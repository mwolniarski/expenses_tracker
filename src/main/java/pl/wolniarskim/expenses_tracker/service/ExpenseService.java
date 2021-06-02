package pl.wolniarskim.expenses_tracker.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;

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

        String text = tesseract.doOCR(image);

//        ------------------------------------------
//        System.out.println(text);
        int startIndex = text.indexOf("FISKALNY");
        int endIndex = text.contains("SUMA PLN") ? text.indexOf("SUMA PLN") : text.indexOf("SUMĄ PLN");

        String expenseAsText = text.substring(startIndex, endIndex);
        String[] strings = expenseAsText.split("\\*");
        Expense expense = new Expense();
        List<ProductLine> productLines = new ArrayList<>();
        int typicalLength = 0;
        for(String a : strings)
            System.out.println(a);
        for(int i=0;i<strings.length;i++){
            String[] line = strings[i].split(" ");
            if(line.length > typicalLength)
                typicalLength = line.length;
        }
        for(int i=0;i<strings.length;i++){
            ProductLine productLine = new ProductLine();
            String[] line = strings[i].split(" ");
            if(i!=0){
                int indexOfUnitPrice = line[0].indexOf(',')+2;
                double unitPrice = Double.valueOf(line[0].substring(0,indexOfUnitPrice).replaceAll(",","."));
                productLines.get(i-1).setUnitPrice(unitPrice);
                if(i!=strings.length-1) {
                    String productName = line[1];
                    Product p = new Product();
                    productLine.setQuantity(Integer.valueOf(line[line.length - 1]));
                    productLines.add(productLine);
                }
            }
            else{
                String productName = line[1];
                Product p = new Product();
                p.setProductName(productName);
                productLine.setProduct(p);
                productLine.setQuantity(Integer.valueOf(line[3]));
                productLines.add(productLine);
            }
        }
        return expense;
    }

    public void deleteExpense(long id){
        if(!expenseRepository.existsById(id))
            throw new EntityNotFoundException();
        expenseRepository.deleteById(id);
    }
}
