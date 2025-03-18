package utils;

import org.junit.jupiter.api.Test;
import com.sales.domain.model.ReceiptSummary;
import com.sales.infra.exceptions.ReceiptFormatterException;
import java.math.BigDecimal;
import com.sales.application.config.PropertyConfig;
import com.sales.application.utils.ReceiptFormattingUtils;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptUtilsTest {

    @Test
    void testGetCurrencyFormat_WithValidConfig() {
        // Simuler une valeur correcte dans PropertyConfig
        PropertyConfig.setProperty("receipt.format.summary", "Taxes: %.2f Total: %.2f");
        String format = ReceiptFormattingUtils.getCurrencyFormat();
        assertEquals("Taxes: %.2f Total: %.2f", format);
    }

    @Test
    void testGetCurrencyFormat_WithEmptyConfig() {
        // Simuler une valeur vide
        PropertyConfig.setProperty("receipt.format.summary", "");
        String format = ReceiptFormattingUtils.getCurrencyFormat();
        assertEquals("Sales Taxes: %.2f Total: %.2f", format);
    }

    @Test
    void testFormatReceiptSummary_WithInvalidFormat() {
        ReceiptSummary summary = new ReceiptSummary(new BigDecimal("1.50"), new BigDecimal("29.83"));
        assertThrows(ReceiptFormatterException.class, () ->
                ReceiptFormattingUtils.formatReceiptSummary(summary, "%s %s %s"));
    }
}
