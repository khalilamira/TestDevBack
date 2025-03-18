package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.amira.application.services.TaxCalculationService;
import tn.amira.domain.model.Product;
import tn.amira.domain.enums.ProductCategory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorImplTest {

    private TaxCalculationService taxCalculator;

    @BeforeEach
    void setUp() {
        taxCalculator = new TaxCalculationService();
    }

    @Test
    void testCalculateTax_ForNonExemptProduct_WithBasicTax() {
        // Produit non exempté -> Taxe de 10%
        Product musicCD = new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER);
        BigDecimal tax = taxCalculator.calculateTax(musicCD);
        assertEquals(new BigDecimal("1.50"), tax);
    }

    @Test
    void testCalculateTax_ForImportedExemptProduct_WithImportTax() {
        // Produit exempté mais importé -> Taxe de 5%
        Product importedChocolate = new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD);
        BigDecimal tax = taxCalculator.calculateTax(importedChocolate);
        assertEquals(new BigDecimal("0.50"), tax);
    }
}
