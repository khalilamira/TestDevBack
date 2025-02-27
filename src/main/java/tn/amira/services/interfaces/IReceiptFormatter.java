package tn.amira.services.interfaces;

import tn.amira.entities.ReceiptItem;
import tn.amira.services.exceptions.ReceiptFormatterException;
import java.util.List;

/**
 * Interface définissant les opérations pour formater un reçu et calculer les montants.
 * <p>
 * Cette interface définit :
 * <ul>
 *     <li>Le formatage du reçu en texte.</li>
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
}
