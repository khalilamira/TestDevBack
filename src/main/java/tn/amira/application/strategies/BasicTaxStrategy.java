package tn.amira.application.strategies;

import tn.amira.domain.model.Product;
import tn.amira.application.ports.TaxStrategy;
import java.math.BigDecimal;

public class BasicTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        return product.getCategory().isExempt() ? BigDecimal.ZERO : product.getPrice().multiply(new BigDecimal("0.10"));
    }
}
