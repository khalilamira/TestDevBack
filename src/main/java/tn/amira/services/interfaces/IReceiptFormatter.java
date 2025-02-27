package tn.amira.services.interfaces;

import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import tn.amira.services.exceptions.ReceiptFormatterException;
import java.util.List;

/**
 * Interface définissant les opérations pour formater un reçu et calculer les montants.
 * <p>
 * Cette interface définit :
 * <ul>
 *     <li>Le formatage du reçu en texte.</li>
 *     <li>Le calcul des totaux (montant total et taxes).</li>
 *     <li>La validation des articles avant génération du reçu.</li>
 * </ul>
 *
 */
public interface IReceiptFormatter {

    /**
     * Formate une liste d'articles sous forme d'un reçu texte.
     *
     * @param items La liste des articles du reçu.
     * @return Une chaîne de caractères représentant le reçu formaté.
     * @throws ReceiptFormatterException Si la liste est vide ou si une erreur survient.
     */
    String formatReceipt(List<ReceiptItem> items) throws ReceiptFormatterException;

    /**
     * Calcule les totaux des taxes et du montant final du reçu.
     *
     * @param items La liste des articles du reçu.
     * @return Un objet {@link ReceiptSummary} contenant les totaux.
     * @throws ReceiptFormatterException Si la liste est vide ou null.
     */
    ReceiptSummary calculateTotals(List<ReceiptItem> items) throws ReceiptFormatterException;
}
