package com.sales.domain.model;

import lombok.Getter;
import com.sales.application.config.PropertyConfig;
import java.math.BigDecimal;

@Getter
public class ReceiptItem {


    private final Product product;
    private final BigDecimal taxAmount;
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