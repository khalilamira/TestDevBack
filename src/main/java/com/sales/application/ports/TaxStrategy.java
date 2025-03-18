package com.sales.application.ports;

import com.sales.domain.model.Product;
import java.math.BigDecimal;


public interface TaxStrategy {
    BigDecimal calculateTax(Product product);
}
