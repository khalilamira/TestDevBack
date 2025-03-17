package tn.amira.services.interfaces;

import tn.amira.entities.Product;
import java.math.BigDecimal;

/**
 * Interface pour le calcul des taxes appliquées aux produits.
 * <p>
 * Cette interface définit un contrat permettant de calculer la taxe applicable à un produit donné.
 * Une implémentation de cette interface doit gérer :
 * <ul>
 *     <li>Les taxes de base applicables à certains types de produits.</li>
 *     <li>Les taxes d'importation pour les produits importés.</li>
 *     <li>L'arrondi du montant des taxes au multiple de 0,05.</li>
 * </ul>
 * <p>
 * Une implémentation typique est {@link tn.amira.services.impls.TaxCalculatorImpl}.
 * </p>
 */
public interface TaxStrategy {
    /**
     * Calcule le montant total des taxes pour un produit donné.
     * <p>
     * La méthode doit prendre en compte :
     * <ul>
     *     <li>Si le produit est exempté de taxe de base ou non.</li>
     *     <li>Si le produit est importé et doit subir une taxe supplémentaire.</li>
     *     <li>Les règles d'arrondi au multiple de 0,05.</li>
     * </ul>
     *
     *
     * @param product Le produit pour lequel la taxe doit être calculée.
     * @return Le montant total des taxes appliquées au produit.
     */
    BigDecimal calculateTax(Product product);
}
