package com.sales.application.services;

import com.sales.application.strategies.StandardTaxStrategy;
import com.sales.application.strategies.ImportedTaxStrategy;
import com.sales.application.utils.TaxUtils;
import com.sales.domain.model.Product;
import com.sales.application.ports.TaxStrategy;
import java.math.BigDecimal;


public class TaxCalculationService implements TaxStrategy {

    private final TaxStrategy basicTaxStrategy = new StandardTaxStrategy();
    private final TaxStrategy importTaxStrategy = new ImportedTaxStrategy();

    @Override
    public BigDecimal calculateTax(Product product) {
        BigDecimal basicTax = basicTaxStrategy.calculateTax(product);
        BigDecimal importTax = importTaxStrategy.calculateTax(product);
        return TaxUtils.roundPrice(basicTax.add(importTax));
    }
}
