package tn.amira;

import tn.amira.domain.model.Product;
import tn.amira.domain.enums.ProductCategory;
import tn.amira.application.services.ReceiptServiceImpl;
import tn.amira.application.services.ReceiptFormatterImpl;
import tn.amira.application.services.TotalsCalculatorImpl;
import tn.amira.application.services.TaxCalculatorImpl;
import tn.amira.application.ports.IReceiptService;
import tn.amira.application.ports.IReceiptFormatter;
import tn.amira.application.ports.ITotalsCalculator;
import tn.amira.application.ports.TaxStrategy;

import java.math.BigDecimal;

public class SalesTaxesApp {
    public static void main(String[] args) {

        // Initialisation des services
        ITotalsCalculator totalsCalculator = new TotalsCalculatorImpl();
        IReceiptFormatter receiptFormatter = new ReceiptFormatterImpl(totalsCalculator);
        TaxStrategy taxCalculator = new TaxCalculatorImpl();

        // Scénario 1
        IReceiptService receipt1 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        receipt1.addProduct(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK));
        receipt1.addProduct(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER));
        receipt1.addProduct(new Product("chocolate bar", new BigDecimal("0.85"), false, ProductCategory.FOOD));
        System.out.println("Output 1:");
        receipt1.printReceipt();

        // Scénario 2
        IReceiptService receipt2 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        receipt2.addProduct(new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD));
        receipt2.addProduct(new Product("imported bottle of perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER));

        System.out.println("\nOutput 2:");
        receipt2.printReceipt();

        // Scénario 3
        IReceiptService receipt3 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        receipt3.addProduct(new Product("imported bottle of perfume", new BigDecimal("27.99"), true, ProductCategory.OTHER));
        receipt3.addProduct(new Product("bottle of perfume", new BigDecimal("18.99"), false, ProductCategory.OTHER));
        receipt3.addProduct(new Product("packet of headache pills", new BigDecimal("9.75"), false, ProductCategory.MEDICAL));
        receipt3.addProduct(new Product("imported box of chocolates", new BigDecimal("11.25"), true, ProductCategory.FOOD));

        System.out.println("\nOutput 3:");
        receipt3.printReceipt();
    }
}
