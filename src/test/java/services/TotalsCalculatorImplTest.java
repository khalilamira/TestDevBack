package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.amira.application.services.ReceiptTotalService;
import tn.amira.domain.model.Product;
import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalsCalculatorImplTest {

    private ReceiptTotalService totalsCalculator;

    @BeforeEach
    void setUp() {
        totalsCalculator = new ReceiptTotalService();
    }

    @Test
    void testCalculateTotals_WithMultipleItems() {
        // Arrange : Création des produits avec leurs taxes
        List<ReceiptItem> items = List.of(
                new ReceiptItem(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK), BigDecimal.ZERO),
                new ReceiptItem(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER), new BigDecimal("1.50")),
                new ReceiptItem(new Product("chocolate bar", new BigDecimal("0.85"), false, ProductCategory.FOOD), BigDecimal.ZERO)
        );

        // Act : Calcul des totaux
        ReceiptSummary summary = totalsCalculator.calculateTotals(items);

        // Assert : Vérifier les valeurs attendues
        assertEquals(new BigDecimal("1.50"), summary.getTotalSalesTaxes());
        assertEquals(new BigDecimal("29.83"), summary.getTotalAmount());
    }

    @Test
    void testCalculateTotals_WithNoItems_ShouldReturnZero() {
        // Act : Calcul avec une liste vide
        ReceiptSummary summary = totalsCalculator.calculateTotals(List.of());

        // Assert : Les valeurs doivent être à zéro
        assertEquals(BigDecimal.ZERO, summary.getTotalSalesTaxes());
        assertEquals(BigDecimal.ZERO, summary.getTotalAmount());
    }

    @Test
    void testCalculateTotals_WithImportedItems() {
        // Arrange : Création des produits importés avec taxes
        List<ReceiptItem> items = List.of(
                new ReceiptItem(new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD), new BigDecimal("0.50")),
                new ReceiptItem(new Product("imported bottle of perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER), new BigDecimal("7.15"))
        );

        // Act : Calcul des totaux
        ReceiptSummary summary = totalsCalculator.calculateTotals(items);

        // Assert : Vérifier les résultats
        assertEquals(new BigDecimal("7.65"), summary.getTotalSalesTaxes());
        assertEquals(new BigDecimal("65.15"), summary.getTotalAmount());
    }
}
