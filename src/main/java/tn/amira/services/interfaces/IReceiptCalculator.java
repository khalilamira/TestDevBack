package tn.amira.services.interfaces;

import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import java.util.List;

/**
 * Interface définissant les opérations de calcul des totaux d'un reçu.
 */
public interface IReceiptCalculator {

    /**
     * Calcule le total des taxes et le montant total du reçu.
     *
     * @param items Liste des articles du reçu.
     * @return Un objet {@link ReceiptSummary} contenant les montants calculés.
     */
    ReceiptSummary calculateTotals(List<ReceiptItem> items);
}
