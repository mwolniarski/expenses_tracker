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
import pl.wolniarskim.expenses_tracker.repository.ExpenseRepository;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    public String uploadFile(MultipartFile file) throws IOException, TesseractException {
        ITesseract tesseract = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
        tesseract.setLanguage("pol");
        tesseract.setOcrEngineMode(2);
        tesseract.setTessVariable("user_defined_dpi", "300");
        tesseract.setPageSegMode(1);
        BufferedImage image = ImageIO.read(file.getInputStream());

        String text = tesseract.doOCR(image);

        return text;
    }

    public void deleteExpense(long id){
        if(!expenseRepository.existsById(id))
            throw new EntityNotFoundException();
        expenseRepository.deleteById(id);
    }
}
