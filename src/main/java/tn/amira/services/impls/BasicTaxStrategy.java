package tn.amira.services.impls;

import tn.amira.entities.Product;
import tn.amira.services.interfaces.TaxStrategy;
import java.math.BigDecimal;

public class BasicTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        return product.getCategory().isExempt() ? BigDecimal.ZERO : product.getPrice().multiply(new BigDecimal("0.10"));
    }
}
