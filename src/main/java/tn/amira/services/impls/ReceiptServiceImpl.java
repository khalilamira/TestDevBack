package tn.amira.services.impls;

import lombok.Getter;
import tn.amira.entities.Product;
import tn.amira.entities.ReceiptItem;
import tn.amira.services.interfaces.ITotalsCalculator;
import tn.amira.services.interfaces.IReceiptFormatter;
import tn.amira.services.interfaces.IReceiptService;
import tn.amira.services.interfaces.TaxStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ReceiptServiceImpl implements IReceiptService {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final TaxStrategy basicTaxStrategy;
    private final TaxStrategy importTaxStrategy;
    private final ITotalsCalculator totalCalculator;
    private final IReceiptFormatter receiptFormatter;

    public ReceiptServiceImpl(TaxStrategy basicTaxStrategy, TaxStrategy importTaxStrategy, IReceiptFormatter receiptFormatter, ITotalsCalculator totalCalculator) {
        this.basicTaxStrategy = basicTaxStrategy;
        this.importTaxStrategy = importTaxStrategy;
        this.receiptFormatter = receiptFormatter;
        this.totalCalculator = totalCalculator;
    }

    public void addProduct(Product product) {
        BigDecimal basicTax = basicTaxStrategy.calculateTax(product);
        BigDecimal importTax = importTaxStrategy.calculateTax(product);
        BigDecimal totalTax = basicTax.add(importTax);

        ReceiptItem item = new ReceiptItem(product, totalTax); // Création correcte de l'objet
        items.add(item);
    }

    public void printReceipt() {
        if (items.isEmpty()) {
            System.out.println("Aucun produit dans le reçu !");
            return;
        }

        System.out.println(receiptFormatter.formatReceipt(items)); // Utilisation du formatter
    }
}
