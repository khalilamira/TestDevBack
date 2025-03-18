package com.sales.application.services;

import lombok.Getter;
import com.sales.domain.model.Product;
import com.sales.domain.model.ReceiptItem;
import com.sales.application.ports.IReceiptFormatter;
import com.sales.application.ports.IReceiptService;
import com.sales.application.ports.TaxStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ReceiptService implements IReceiptService {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final TaxStrategy taxCalculator;
    private final IReceiptFormatter receiptFormatter;

    public ReceiptService(TaxStrategy taxCalculator, IReceiptFormatter receiptFormatter) {
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
