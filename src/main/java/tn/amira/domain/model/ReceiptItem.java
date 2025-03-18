package tn.amira.domain.model;

import lombok.Getter;
import tn.amira.application.config.PropertyConfig;
import java.math.BigDecimal;

public class ReceiptItem {


    private final Product product;
    @Getter
    private final BigDecimal taxAmount;
    @Getter
    private final BigDecimal totalPrice;


    private static final String LINE_FORMAT = PropertyConfig.getProperty("receipt.format.line");

    public ReceiptItem(Product product, BigDecimal taxAmount) {
        this.product = product;
        this.taxAmount = taxAmount;
        this.totalPrice = product.getPrice().add(taxAmount);
    }

    public String format() {
        return String.format(LINE_FORMAT, product.getName(), totalPrice);
    }
}