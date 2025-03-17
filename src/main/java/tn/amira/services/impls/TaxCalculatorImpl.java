package tn.amira.services.impls;

import tn.amira.entities.Product;
import tn.amira.services.interfaces.TaxStrategy;
import tn.amira.services.impls.BasicTaxStrategy;
import tn.amira.services.impls.ImportTaxStrategy;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculatorImpl implements TaxStrategy {

    private final TaxStrategy basicTaxStrategy = new BasicTaxStrategy();
    private final TaxStrategy importTaxStrategy = new ImportTaxStrategy();

    @Override
    public BigDecimal calculateTax(Product product) {
        BigDecimal basicTax = basicTaxStrategy.calculateTax(product);
        BigDecimal importTax = importTaxStrategy.calculateTax(product);
        return roundPrice(basicTax.add(importTax));
    }

    private BigDecimal roundPrice(BigDecimal amount) {
        final BigDecimal rounding = BigDecimal.valueOf(0.05);
        return amount.divide(rounding, 0, RoundingMode.CEILING)
                .multiply(rounding);
    }
}
