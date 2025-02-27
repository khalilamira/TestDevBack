package tn.amira.services.interfaces;

import tn.amira.entities.Product;

/**
 * Interface définissant les opérations de gestion des reçus.
 * <p>
 * Cette interface permet :
 * <ul>
 *     <li>D'ajouter un produit au reçu avec le calcul de la taxe associée.</li>
 *     <li>De générer un reçu formaté incluant tous les produits et les taxes appliquées.</li>
 * </ul>
 *
 * <p>Une implémentation typique de cette interface est {@code ReceiptServiceImpl}.</p>
 */
public interface IReceiptService {
    /**
     * Ajoute un produit au reçu en appliquant les taxes associées.
     *
     * @param product Le produit à ajouter au reçu.
     */
    void addProduct(Product product);
    /**
     * Génère et retourne le reçu sous forme de texte formaté.
     * <p>
     * Le reçu inclut :
     * <ul>
     *     <li>La liste des produits avec leurs prix (incluant taxes).</li>
     *     <li>Le montant total des taxes.</li>
     *     <li>Le prix total des produits achetés.</li>
     * </ul>
     *
     * @return Une chaîne de caractères représentant le reçu formaté.
     */
    String getReceipt();
}
