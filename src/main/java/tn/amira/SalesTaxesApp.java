package tn.amira;

import tn.amira.entities.Product;
import tn.amira.entities.enums.ProductCategory;
import tn.amira.services.impls.ReceiptServiceImpl;
import tn.amira.services.impls.BasicTaxStrategy;
import tn.amira.services.impls.ImportTaxStrategy;
import tn.amira.services.impls.ReceiptFormatterImpl;
import tn.amira.services.impls.TotalsCalculatorImpl;
import tn.amira.services.interfaces.IReceiptService;
import tn.amira.services.interfaces.TaxStrategy;
import tn.amira.services.interfaces.IReceiptFormatter;
import tn.amira.services.interfaces.ITotalsCalculator;
import java.math.BigDecimal;

public class SalesTaxesApp {
    public static void main(String[] args) {
        // Injection des stratégies de taxes
        TaxStrategy basicTax = new BasicTaxStrategy();
        TaxStrategy importTax = new ImportTaxStrategy();

        // Services supplémentaires
        ITotalsCalculator totalsCalculator = new TotalsCalculatorImpl();
        String receiptFormat = "%s : %.2f"; // Format du reçu
        IReceiptFormatter receiptFormatter = new ReceiptFormatterImpl(totalsCalculator, receiptFormat);

        // Création du service de gestion du reçu
        IReceiptService receipt1 = new ReceiptServiceImpl(basicTax, importTax, receiptFormatter, totalsCalculator);

        // Ajout des produits (exemple Input 1)
        receipt1.addProduct(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK));
        receipt1.addProduct(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER));
        receipt1.addProduct(new Product("chocolate bar", new BigDecimal("0.85"), false, ProductCategory.FOOD));
        System.out.println("Output 1:");
        receipt1.printReceipt();

        // Scénario 2
        IReceiptService receipt2 = new ReceiptServiceImpl(basicTax, importTax, receiptFormatter, totalsCalculator);
        receipt2.addProduct(new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD));
        receipt2.addProduct(new Product("imported bottle of perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER));

        System.out.println("\nOutput 2:");
        receipt2.printReceipt();

        // Scénario 3
        IReceiptService receipt3 = new ReceiptServiceImpl(basicTax, importTax, receiptFormatter, totalsCalculator);
        receipt3.addProduct(new Product("imported bottle of perfume", new BigDecimal("27.99"), true, ProductCategory.OTHER));
        receipt3.addProduct(new Product("bottle of perfume", new BigDecimal("18.99"), false, ProductCategory.OTHER));
        receipt3.addProduct(new Product("packet of headache pills", new BigDecimal("9.75"), false, ProductCategory.MEDICAL));
        receipt3.addProduct(new Product("imported box of chocolates", new BigDecimal("11.25"), true, ProductCategory.FOOD));

        System.out.println("\nOutput 3:");
        receipt3.printReceipt();
    }
}
