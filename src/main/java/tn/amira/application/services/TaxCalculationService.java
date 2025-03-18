package tn.amira.application.services;

import tn.amira.application.strategies.StandardTaxStrategy;
import tn.amira.application.strategies.ImportedTaxStrategy;
import tn.amira.application.utils.TaxUtils;
import tn.amira.domain.model.Product;
import tn.amira.application.ports.TaxStrategy;
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
