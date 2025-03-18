package tn.amira.application.strategies;

import tn.amira.domain.model.Product;
import tn.amira.application.ports.TaxStrategy;
import java.math.BigDecimal;

public class ImportTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        return product.isImported() ? product.getPrice().multiply(new BigDecimal("0.05")) : BigDecimal.ZERO;
    }
}
