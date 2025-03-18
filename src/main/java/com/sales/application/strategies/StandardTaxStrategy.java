package com.sales.application.strategies;

import com.sales.domain.model.Product;
import com.sales.application.ports.TaxStrategy;
import java.math.BigDecimal;

public class StandardTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        return product.getCategory().isExempt() ? BigDecimal.ZERO : product.getPrice().multiply(new BigDecimal("0.10"));
    }
}
