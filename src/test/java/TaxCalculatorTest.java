import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.amira.config.MessageConfig;
import tn.amira.entities.Product;
import tn.amira.entities.enums.ProductCategory;
import tn.amira.services.impls.TaxCalculatorImpl;
import tn.amira.services.interfaces.ITaxCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaire pour {@link TaxCalculatorImpl}.
 * <p>
 * Ce test vérifie le calcul des taxes pour différents types de produits :
 * <ul>
 *     <li>Produits exemptés de taxe de base.</li>
 *     <li>Produits soumis à la taxe de base.</li>
 *     <li>Produits importés soumis à la taxe d'importation.</li>
 *     <li>Produits soumis à la fois à la taxe de base et à la taxe d'importation.</li>
 * </ul>
 * </p>
 */
class TaxCalculatorTest {

    private ITaxCalculator taxCalculator;

    // Définition des constantes pour les taux de taxe
    private static final BigDecimal ZERO_TAX = BigDecimal.ZERO;
    private static final BigDecimal BASIC_TAX_RATE = new BigDecimal("1.50");
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.50");
    private static final BigDecimal BOTH_TAX_RATE = new BigDecimal("7.15");

    /**
     * Initialise l'instance de {@link TaxCalculatorImpl} avant chaque test.
     */
    @BeforeEach
    public void setup() {
        taxCalculator = new TaxCalculatorImpl();
    }

    /**
     * Vérifie que les produits exemptés de taxe (ex : livres) ne sont pas taxés.
     */
    @Test
    void testExemptProduct_NoTax() {
        // Given
        Product book = new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK);

        // When
        BigDecimal tax = taxCalculator.calculateTax(book);

        // Then
        assertEquals(0,tax.compareTo(ZERO_TAX) , MessageConfig.getMessage("test.tax.exemptProduct"));

    }

    /**
     * Vérifie que les produits non exemptés (ex : CD de musique) sont soumis à une taxe de base de 10%.
     */
    @Test
    void testNonExemptProduct_BasicTax() {
        // Given
        Product cd = new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER);

        // When
        BigDecimal tax = taxCalculator.calculateTax(cd);

        // Then
        assertEquals(BASIC_TAX_RATE, tax, MessageConfig.getMessage("test.tax.nonExemptProduct"));
    }

    /**
     * Vérifie que les produits importés mais exemptés (ex : chocolat importé) sont taxés uniquement à 5%.
     */
    @Test
    void testImportedProduct_ImportTaxOnly() {
        // Given
        Product importedChocolate = new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD);

        // When
        BigDecimal tax = taxCalculator.calculateTax(importedChocolate);

        // Then
        assertEquals(IMPORT_TAX_RATE, tax, MessageConfig.getMessage("test.tax.importedProduct"));
    }

    /**
     * Vérifie que les produits non exemptés et importés (ex : parfum importé)
     * sont soumis à la taxe de base (10%) et à la taxe d'importation (5%).
     */
    @Test
    void testImportedNonExemptProduct_BothTaxes() {
        // Given
        Product importedPerfume = new Product("imported bottle of perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER);

        // When
        BigDecimal tax = taxCalculator.calculateTax(importedPerfume);

        // Then
        assertEquals(BOTH_TAX_RATE, tax, MessageConfig.getMessage("test.tax.importedNonExemptProduct"));
    }

}
