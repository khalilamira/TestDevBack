package tn.amira.domain.model;

import java.math.BigDecimal;


public class ReceiptSummary {


    private final BigDecimal totalSalesTaxes;
    private final BigDecimal totalAmount;


    public ReceiptSummary(BigDecimal totalSalesTaxes, BigDecimal totalAmount) {
        this.totalSalesTaxes = totalSalesTaxes;
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalSalesTaxes() {
        return totalSalesTaxes;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}


