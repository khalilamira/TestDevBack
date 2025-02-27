package tn.amira.services.impls;

import tn.amira.config.MessageConfig;
import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import tn.amira.services.exceptions.ReceiptFormatterException;
import tn.amira.services.interfaces.IReceiptCalculator;
import tn.amira.services.interfaces.IReceiptFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implémentation de {@link IReceiptFormatter} pour formater un reçu sous forme de texte lisible.
 * <p>
 * Cette classe reçoit une liste d'articles, calcule les montants à l'aide de {@link IReceiptCalculator},
 * et génère une sortie formatée avec les détails du reçu.
 * </p>
 */
public class ReceiptFormatterImpl implements IReceiptFormatter {

    private final IReceiptCalculator receiptCalculator;
    private final String currencyFormat;

    /**
     * Constructeur permettant d'injecter les dépendances nécessaires.
     *
     * @param receiptCalculator Service de calcul des totaux.
     * @param currencyFormat    Format à utiliser dans l'affichage du reçu.
     */
    public ReceiptFormatterImpl(IReceiptCalculator receiptCalculator, String currencyFormat) {
        this.receiptCalculator = receiptCalculator;
        this.currencyFormat = currencyFormat;
    }

    /**
     * Formate une liste d'articles sous forme de reçu lisible.
     *
     * @param items Liste des articles du reçu.
     * @return Une chaîne formatée représentant le reçu.
     * @throws ReceiptFormatterException Si la liste des articles est vide ou si un problème de formatage survient.
     */
    @Override
    public String formatReceipt(List<ReceiptItem> items) {
        validateItems(items);
        ReceiptSummary summary = receiptCalculator.calculateTotals(items);
        return generateFormattedReceipt(items, summary);
    }

    /**
     * Vérifie que la liste d'articles n'est ni vide ni null.
     *
     * @param items Liste des articles du reçu.
     * @throws ReceiptFormatterException Si la liste est vide ou null.
     */
    private void validateItems(List<ReceiptItem> items) {
        if (items == null || items.isEmpty()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.emptyList"));
        }
    }

    /**
     * Génère le reçu formaté à partir des articles et du résumé des montants calculés.
     *
     * @param items   Liste des articles du reçu.
     * @param summary Résumé des montants calculés.
     * @return Une chaîne formatée représentant le reçu.
     * @throws ReceiptFormatterException Si le format de la devise est invalide.
     */
    private String generateFormattedReceipt(List<ReceiptItem> items, ReceiptSummary summary) {
        if (Objects.isNull(currencyFormat) || currencyFormat.isBlank()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.invalidFormat"));
        }

        String formattedItems = formatItems(items);
        return formattedItems + "\n" + formatSummary(summary);
    }

    /**
     * Formate la liste des articles du reçu sous forme de texte.
     *
     * @param items Liste des articles du reçu.
     * @return Une chaîne contenant tous les articles formatés.
     */
    private String formatItems(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::format)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Formate le résumé du reçu (taxes et total).
     *
     * @param summary Résumé des montants calculés.
     * @return Une chaîne formatée avec les taxes et le total.
     * @throws ReceiptFormatterException En cas d'erreur lors du formatage du résumé.
     */
    private String formatSummary(ReceiptSummary summary) {
        try {
            return String.format(currencyFormat, summary.getTotalSalesTaxes(), summary.getTotalAmount());
        } catch (Exception e) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.formatError"), e);
        }
    }
}
