package tn.amira.services.impls;

import tn.amira.entities.Product;
import tn.amira.services.interfaces.TaxStrategy;
import java.math.BigDecimal;

public class ImportTaxStrategy implements TaxStrategy {
    @Override
    public BigDecimal calculateTax(Product product) {
        return product.isImported() ? product.getPrice().multiply(new BigDecimal("0.05")) : BigDecimal.ZERO;
    }
}
