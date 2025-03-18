package tn.amira.application.services;

import lombok.Getter;
import tn.amira.domain.model.Product;
import tn.amira.domain.model.ReceiptItem;
import tn.amira.application.ports.IReceiptFormatter;
import tn.amira.application.ports.IReceiptService;
import tn.amira.application.ports.TaxStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ReceiptServiceImpl implements IReceiptService {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final TaxStrategy taxCalculator;
    private final IReceiptFormatter receiptFormatter;

    public ReceiptServiceImpl(TaxStrategy taxCalculator, IReceiptFormatter receiptFormatter) {
        this.taxCalculator = taxCalculator;
        this.receiptFormatter = receiptFormatter;
    }

    public void addProduct(Product product) {
        BigDecimal totalTax = taxCalculator.calculateTax(product);
        ReceiptItem item = new ReceiptItem(product, totalTax);
        items.add(item);
    }

    public void printReceipt() {
        if (items.isEmpty()) {
            System.out.println("Aucun produit dans le reçu !");
            return;
        }
        System.out.println(receiptFormatter.formatReceipt(items));
    }
}
