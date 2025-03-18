package tn.amira.application.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class TaxUtils {

    public static  BigDecimal roundPrice(BigDecimal amount) {
        final BigDecimal rounding = BigDecimal.valueOf(0.05);
        return amount.divide(rounding, 0, RoundingMode.CEILING)
                .multiply(rounding);
    }
}
