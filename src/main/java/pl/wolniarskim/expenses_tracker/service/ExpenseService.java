package pl.wolniarskim.expenses_tracker.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.model.Product;
import pl.wolniarskim.expenses_tracker.model.ProductLine;
import pl.wolniarskim.expenses_tracker.repository.ExpenseRepository;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    private Logger logger = LoggerFactory.getLogger("Expense service");

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense){
        Expense returnedExpense = expenseRepository.save(expense);
        return returnedExpense;
    }

    public Expense uploadFile(MultipartFile file) throws IOException, TesseractException {
        ITesseract tesseract = new Tesseract();

        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("pol");
        tesseract.setOcrEngineMode(2);
        tesseract.setTessVariable("user_defined_dpi", "300");
        tesseract.setPageSegMode(1);
        BufferedImage image = ImageIO.read(file.getInputStream());

//        ImageIO.write(image, "jpg", new File("C:\\Users\\Lenovo\\Pictures\\image1.jpg"));

        String text = tesseract.doOCR(image);
        System.out.println(text);
//        ------------------------------------------
//        System.out.println(text);
        int startIndex = text.indexOf("ISKALNY");
        int endIndex = (text.contains("SUMA") ? text.indexOf("SUMA") : text.indexOf("SUMĄ"));
        String expenseAsText = text.substring(startIndex, endIndex);
        System.out.println(expenseAsText);

        double paymentAmount = 0;9p0
        String[] strings = expenseAsText.split("\\n");
        Expense expense = new Expense();
        expense.setPurchaseDate(LocalDate.now());
        List<ProductLine> productLines = new ArrayList<>();
        for(int i=0;i<strings.length;i++){
            if(strings[i].contains("*") || strings[i].contains("x")) {
                ProductLine productLine = new ProductLine();
                String[] line = strings[i].split(" ");
                Product product = new Product();
                product.setProductName(line[0]);
                int counter = 0;
                for(String a : line){
                    try {
                        if (a.contains("*")) {
                            String[] splitedLine = a.split("\\*");
                            int quantity = Integer.parseInt(splitedLine[0]);
                            String unitPriceAsText = splitedLine[1].substring(0, splitedLine[1].indexOf(',') + 3).replaceAll(",", ".");
                            double unitPrice = Double.parseDouble(unitPriceAsText);
                            paymentAmount += unitPrice;
                            productLine.setProduct(product);
                            productLine.setQuantity(quantity);
                            productLine.setUnitPrice(unitPrice);
                            productLines.add(productLine);
                        } else if (a.contains("x")) {
                            String[] splitedLine = a.split("x");
                            String qua;
                            if (splitedLine[0].equals(""))
                                qua = line[counter - 1];
                            else
                                qua = splitedLine[0];
                            int quantity = Integer.parseInt(qua);
                            String unitPriceAsText = splitedLine[1].substring(0, splitedLine[1].indexOf(',') + 3).replaceAll(",", ".");
                            double unitPrice = Double.parseDouble(unitPriceAsText);
                            paymentAmount += unitPrice;
                            productLine.setProduct(product);
                            productLine.setQuantity(quantity);
                            productLine.setUnitPrice(unitPrice);
                            productLines.add(productLine);
                        }
                    }
                    catch(NumberFormatException ex){
                        logger.error(ex.getMessage());
                    }
                    counter++;
                }
            }
        }
        expense.setPaymentAmount(paymentAmount);
        expense.setProductLines(productLines);
        return expense;
    }

    public void deleteExpense(long id){
        if(!expenseRepository.existsById(id))
            throw new EntityNotFoundException();
        expenseRepository.deleteById(id);
    }
}
