package tn.amira.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.amira.application.config.MessageConfig;
import tn.amira.application.config.PropertyConfig;
import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.infra.exceptions.ReceiptFormatterException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ReceiptUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptUtils.class);

    private ReceiptUtils() {
    }

    public static void validateItems(List<ReceiptItem> items) {
        if (items == null || items.isEmpty()) {
            LOGGER.error("Le reçu est vide, impossible de formater.");
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.emptyList"));
        }
    }

    public static String formatReceiptItems(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::format)
                .collect(Collectors.joining("\n"));
    }

    public static String formatReceiptSummary(ReceiptSummary summary, String currencyFormat) {
        try {
            return String.format(currencyFormat, summary.getTotalSalesTaxes(), summary.getTotalAmount());
        } catch (Exception e) {
            LOGGER.error("Erreur de formatage du reçu.", e);
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.formatError"), e);
        }
    }

    public static String getCurrencyFormat() {
        String format = PropertyConfig.getProperty("receipt.format.summary");
        if (Objects.isNull(format) || format.isBlank()) {
            LOGGER.warn("Le format de currency est vide, utilisation d'un format par défaut.");
            return "Sales Taxes: %.2f Total: %.2f"; // Valeur par défaut
        }
        return format;
    }
}
