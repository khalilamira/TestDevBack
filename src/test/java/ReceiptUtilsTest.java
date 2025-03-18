

import org.junit.jupiter.api.Test;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.infra.exceptions.ReceiptFormatterException;
import java.math.BigDecimal;
import tn.amira.application.config.PropertyConfig;
import tn.amira.application.utils.ReceiptUtils;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptUtilsTest {

    @Test
    void testGetCurrencyFormat_WithValidConfig() {
        // Simuler une valeur correcte dans PropertyConfig
        PropertyConfig.setProperty("receipt.format.summary", "Taxes: %.2f Total: %.2f");
        String format = ReceiptUtils.getCurrencyFormat();
        assertEquals("Taxes: %.2f Total: %.2f", format);
    }

    @Test
    void testGetCurrencyFormat_WithEmptyConfig() {
        // Simuler une valeur vide
        PropertyConfig.setProperty("receipt.format.summary", "");
        String format = ReceiptUtils.getCurrencyFormat();
        assertEquals("Sales Taxes: %.2f Total: %.2f", format);
    }

    @Test
    void testFormatReceiptSummary_WithInvalidFormat() {
        ReceiptSummary summary = new ReceiptSummary(new BigDecimal("1.50"), new BigDecimal("29.83"));
        assertThrows(ReceiptFormatterException.class, () ->
                ReceiptUtils.formatReceiptSummary(summary, "%s %s %s"));
    }
}
