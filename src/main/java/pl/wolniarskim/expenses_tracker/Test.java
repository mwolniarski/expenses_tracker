package pl.wolniarskim.expenses_tracker;

import org.springframework.security.core.parameters.P;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.model.Product;
import pl.wolniarskim.expenses_tracker.model.ProductLine;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String text = "kik TT i 1 3p. z 0.0.\n" +
                "ul. tegnicka 21a\n" +
                "53—671 Wroclaw\n" +
                "KiK 7186\n" +
                "ul. Pociskowa 4\n" +
                "04—481 Warszawa Rembertów\n" +
                "NIP 897—17—77—132\n" +
                "2021—05—24 nr wydr 006288\n" +
                "PARAGON FISKALNY\n" +
                "CEDZAK A 1*4,00#= 4,00 A\n" +
                "1139679.913.10030,00100\n" +
                "LAMPA SOLARNA 4. A 1*12,99= 12,99 &\n" +
                "1134002.628.10010,00299\n" +
                "LAMPA SOLARNA — A 1*12,99= 12,99 &\n" +
                "1134002.628.10010.,00299\n" +
                "KOSZYK OGRODOWY A 1*12,99= 12,99 4\n" +
                "\n" +
                "1136875.628.10010.00299\n" +
                "" +
                " A 1*0,65= 0,65 A\n" +
                "\n" +
                "5896600.611.00065\n" +
                "\n" +
                "Sprzed. opod. PTU A 43,62\n" +
                "Kwota A 23,00% 8,16\n" +
                "Podatek PTU 8,16\n" +
                "\n" +
                "SUMĄ PLN 43,62";
        int startIndex = text.indexOf("FISKALNY");
        int endIndex = (text.contains("SUMA PLN") ? text.indexOf("SUMA PLN") : text.indexOf("SUMĄ PLN"));

        String expenseAsText = text.substring(startIndex, endIndex);
        System.out.println(expenseAsText);
        
        String[] strings = expenseAsText.split("\\n");
        Expense expense = new Expense();
        List<ProductLine> productLines = new ArrayList<>();
        for(String a : strings)
            System.out.println(a);
        for(int i=0;i<strings.length;i++){
            if(strings[i].contains("*")) {
                ProductLine productLine = new ProductLine();
                String[] line = strings[i].split(" ");
                Product product = new Product();
                product.setProductName(line[0]);
                for(String a : line){
                    if(a.contains("*")){
                        String[] splitedLine = a.split("\\*");
                        int quantity = Integer.valueOf(splitedLine[0]);
                        String unitPriceAsText = splitedLine[1].substring(0,splitedLine[1].indexOf(',')+2).replaceAll(",",".");
                        double unitPrice = Double.valueOf(unitPriceAsText);
                        productLine.setProduct(product);
                        productLine.setQuantity(quantity);
                        productLine.setUnitPrice(unitPrice);
                        productLines.add(productLine);
                    }
                }
            }
        }
        expense.setProductLines(productLines);
    }
}
