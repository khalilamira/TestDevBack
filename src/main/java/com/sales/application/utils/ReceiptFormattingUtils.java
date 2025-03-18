package com.sales.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sales.application.config.MessageConfig;
import com.sales.application.config.PropertyConfig;
import com.sales.domain.model.ReceiptItem;
import com.sales.domain.model.ReceiptSummary;
import com.sales.infra.exceptions.ReceiptFormatterException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ReceiptFormattingUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptFormattingUtils.class);

    private ReceiptFormattingUtils() {
    }

    /**
     * Valide la liste des articles du reçu.
     * Vérifie si la liste est vide ou nulle et lève une exception si c'est le cas.
     *
     * @param items Liste des articles du reçu.
     * @throws ReceiptFormatterException si la liste est vide ou nulle.
     */
    public static void validateItems(List<ReceiptItem> items) {
        if (items == null || items.isEmpty()) {
            LOGGER.error("Le reçu est vide, impossible de formater.");
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.emptyList"));
        }
    }

    /**
     * Génère une représentation formatée des articles du reçu.
     * Chaque article est transformé en chaîne et concaténé avec un saut de ligne.
     *
     * @param items Liste des articles du reçu.
     * @return Une chaîne formatée représentant tous les articles du reçu.
     */
    public static String formatReceiptItems(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::format)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Génère une représentation formatée du résumé du reçu.
     * Utilise un format spécifié pour afficher les taxes et le montant total.
     *
     * @param summary       Résumé du reçu contenant le total des taxes et le montant total.
     * @param currencyFormat Format utilisé pour l'affichage (ex: "Sales Taxes: %.2f Total: %.2f").
     * @return Une chaîne formatée du résumé du reçu.
     * @throws ReceiptFormatterException si le formatage échoue.
     */
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
