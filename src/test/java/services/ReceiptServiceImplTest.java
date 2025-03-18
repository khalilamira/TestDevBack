package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sales.application.ports.IReceiptFormatter;
import com.sales.application.ports.TaxStrategy;
import com.sales.application.services.ReceiptService;
import com.sales.domain.model.Product;
import com.sales.domain.model.ReceiptItem;
import com.sales.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptServiceImplTest {

    private ReceiptService receiptService;
    private TaxStrategy taxCalculatorMock;
    private IReceiptFormatter receiptFormatterMock;

    @BeforeEach
    void setUp() {
        taxCalculatorMock = mock(TaxStrategy.class);
        receiptFormatterMock = mock(IReceiptFormatter.class);
        receiptService = new ReceiptService(taxCalculatorMock, receiptFormatterMock);
    }

    @Test
    void testAddProduct_ShouldAddItemToReceipt() {
        // Arrange
        Product product = new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER);
        when(taxCalculatorMock.calculateTax(product)).thenReturn(new BigDecimal("1.50"));

        // Act
        receiptService.addProduct(product);

        // Assert
        List<ReceiptItem> items = receiptService.getItems();
        assertEquals(1, items.size());
        assertEquals(product, items.get(0).getProduct());
        assertEquals(new BigDecimal("1.50"), items.get(0).getTaxAmount());
    }

    @Test
    void testPrintReceipt_ShouldCallFormatterWhenItemsExist() {
        // Arrange
        Product product = new Product("imported perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER);
        when(taxCalculatorMock.calculateTax(product)).thenReturn(new BigDecimal("7.15"));
        receiptService.addProduct(product);

        when(receiptFormatterMock.formatReceipt(anyList())).thenReturn("Formatted Receipt Output");

        // Act
        receiptService.printReceipt();

        // Assert
        verify(receiptFormatterMock, times(1)).formatReceipt(anyList());
    }

    @Test
    void testPrintReceipt_WithEmptyReceipt_ShouldNotCallFormatter() {
        // Act
        receiptService.printReceipt();

        // Assert
        verify(receiptFormatterMock, never()).formatReceipt(anyList());
    }
}
