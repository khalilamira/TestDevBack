package tn.amira.application.ports;

import tn.amira.domain.model.Product;
import java.math.BigDecimal;


public interface TaxStrategy {
    BigDecimal calculateTax(Product product);
}
