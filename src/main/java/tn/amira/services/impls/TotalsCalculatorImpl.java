package tn.amira.services.impls;

import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import tn.amira.services.interfaces.ITotalsCalculator;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service responsable du calcul des totaux d'un reçu.
 */
public class TotalsCalculatorImpl implements ITotalsCalculator {

    /**
     * Calcule le total des taxes et le montant total à payer.
     *
     * @param items Liste des articles du reçu.
     * @return Un objet {@link ReceiptSummary} contenant les montants calculés.
     */
    public ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        BigDecimal totalSalesTaxes = items.stream()
                .map(ReceiptItem::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAmount = items.stream()
                .map(ReceiptItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ReceiptSummary(totalSalesTaxes, totalAmount);
    }
}
