package tn.amira.application.utils;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;

import java.math.BigDecimal;
import java.util.List;

public final class ReceiptCalculationUtils {

    private ReceiptCalculationUtils() {
        // Constructeur privé pour empêcher l'instanciation
    }

    /**
     * Calcule le total des taxes appliquées sur tous les articles du reçu.
     * @param items Liste des éléments du reçu
     * @return Somme des taxes
     */
    public static BigDecimal calculateTotalSalesTaxes(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcule le montant total du reçu (prix des produits + taxes).
     * @param items Liste des éléments du reçu
     * @return Montant total
     */
    public static BigDecimal calculateTotalAmount(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcule les totaux et retourne un objet `ReceiptSummary`.
     * @param items Liste des éléments du reçu
     * @return Objet `ReceiptSummary` contenant les totaux calculés
     */
    public static ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        BigDecimal totalSalesTaxes = calculateTotalSalesTaxes(items);
        BigDecimal totalAmount = calculateTotalAmount(items);
        return new ReceiptSummary(totalSalesTaxes, totalAmount);
    }
}
