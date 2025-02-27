package tn.amira.services.impls;

import tn.amira.config.MessageConfig;
import tn.amira.config.PropertyConfig;
import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import tn.amira.services.exceptions.ReceiptFormatterException;
import tn.amira.services.interfaces.IReceiptFormatter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Implémentation de {@link IReceiptFormatter} pour formater un reçu sous forme de texte.
 * <p>
 * Cette classe est responsable uniquement du formatage du reçu,
 * tandis que le calcul des montants est délégué à la méthode {@code calculateTotals()}.
 * </p>
 */
public class ReceiptFormatterImpl implements IReceiptFormatter {

    /**
     * Format utilisé pour afficher les montants du reçu.
     */
    private static final String CURRENCY_FORMAT = PropertyConfig.getProperty("receipt.format.currency");

    /**
     * Formate une liste d'articles du reçu en une chaîne de caractères lisible.
     *
     * @param items La liste des articles du reçu.
     * @return Une chaîne de caractères représentant le reçu formaté.
     * @throws ReceiptFormatterException Si la liste d'articles est vide ou si une erreur de formatage survient.
     */
    @Override
    public String formatReceipt(List<ReceiptItem> items) {
        validateItems(items);

        ReceiptSummary summary = calculateTotals(items);

        return generateFormattedReceipt(items, summary);
    }


    /**
     * Valide la liste des articles avant de générer le reçu.
     *
     * @param items Liste des articles à vérifier.
     * @throws ReceiptFormatterException Si la liste est vide ou null.
     */
    private void validateItems(List<ReceiptItem> items) {
        if (items == null || items.isEmpty()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.emptyList"));
        }
    }

    /**
     * Calcule le total des taxes et le montant total à payer.
     *
     * @param items Liste des articles du reçu.
     * @return Un objet {@link ReceiptSummary} contenant les montants calculés.
     */
    @Override
    public ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        BigDecimal totalSalesTaxes = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (ReceiptItem item : items) {
            totalSalesTaxes = totalSalesTaxes.add(item.getTaxAmount());
            totalAmount = totalAmount.add(item.getTotalPrice());
        }

        return new ReceiptSummary(totalSalesTaxes, totalAmount);
    }


    /**
     * Génère le reçu formaté à partir des articles et des totaux calculés.
     *
     * @param items   Liste des articles du reçu.
     * @param summary Résumé des montants calculés.
     * @return Une chaîne formatée représentant le reçu.
     */
    private String generateFormattedReceipt(List<ReceiptItem> items, ReceiptSummary summary) {
        StringBuilder sb = new StringBuilder();

        for (ReceiptItem item : items) {
            sb.append(item.format()).append("\n");
        }

        if (Objects.isNull(CURRENCY_FORMAT) || CURRENCY_FORMAT.isBlank()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.invalidFormat"));
        }

        try {
            sb.append(String.format(CURRENCY_FORMAT, summary.getTotalSalesTaxes(), summary.getTotalAmount()));
        } catch (Exception e) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.formatError"), e);
        }

        return sb.toString();
    }
}
