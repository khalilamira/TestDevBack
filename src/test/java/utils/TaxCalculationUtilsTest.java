package utils;

import org.junit.jupiter.api.Test;
import com.sales.application.utils.TaxUtils;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculationUtilsTest {

    @Test
    void testRoundPrice_WithExactMultipleOfFiveCents() {
        assertEquals(new BigDecimal("1.50"), TaxUtils.roundPrice(new BigDecimal("1.50")));
    }

    @Test
    void testRoundPrice_WithValueNeedingRoundingUp() {
        assertEquals(new BigDecimal("1.45"), TaxUtils.roundPrice(new BigDecimal("1.42")));
    }

    @Test
    void testRoundPrice_WithValueAtExactBoundary() {
        assertEquals(new BigDecimal("1.50"), TaxUtils.roundPrice(new BigDecimal("1.46")));
    }
}
