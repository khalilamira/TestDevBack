package utils;

import org.junit.jupiter.api.Test;
import com.sales.application.utils.ReceiptCalculationUtils;
import com.sales.domain.model.Product;
import com.sales.domain.model.ReceiptItem;
import com.sales.domain.model.ReceiptSummary;
import com.sales.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptCalculationUtilsTest {

    @Test
    void testCalculateTotalSalesTaxes() {
        List<ReceiptItem> items = List.of(
                new ReceiptItem(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK), BigDecimal.ZERO),
                new ReceiptItem(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER), new BigDecimal("1.50"))
        );

        BigDecimal totalTaxes = ReceiptCalculationUtils.calculateTotalSalesTaxes(items);
        assertEquals(new BigDecimal("1.50"), totalTaxes);
    }

    @Test
    void testCalculateTotalAmount() {
        List<ReceiptItem> items = List.of(
                new ReceiptItem(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK), BigDecimal.ZERO),
                new ReceiptItem(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER), new BigDecimal("1.50"))
        );

        BigDecimal totalAmount = ReceiptCalculationUtils.calculateTotalAmount(items);
        assertEquals(new BigDecimal("28.98"), totalAmount);
    }

    @Test
    void testCalculateTotals() {
        List<ReceiptItem> items = List.of(
                new ReceiptItem(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK), BigDecimal.ZERO),
                new ReceiptItem(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER), new BigDecimal("1.50"))
        );

        ReceiptSummary summary = ReceiptCalculationUtils.calculateTotals(items);
        assertEquals(new BigDecimal("1.50"), summary.getTotalSalesTaxes());
        assertEquals(new BigDecimal("28.98"), summary.getTotalAmount());
    }
}
