package com.sales.domain.enums;

/**
 * Catégories de produits disponibles dans le système de taxation.
 * <p>
 * Ces catégories permettent de déterminer si un produit est exempté de taxe de base
 * ou s'il doit être soumis à des taxes spécifiques.
 * </p>
 *
 * <ul>
 *     <li>{@link #BOOK} - Produits de type livre, généralement exemptés de taxe de base.</li>
 *     <li>{@link #FOOD} - Produits alimentaires, généralement exemptés de taxe de base.</li>
 *     <li>{@link #MEDICAL} - Produits médicaux, généralement exemptés de taxe de base.</li>
 *     <li>{@link #OTHER} - Tous les autres produits soumis à la taxe de base.</li>
 * </ul>
 */
public enum ProductCategory {
    BOOK, FOOD, MEDICAL, OTHER;

    public boolean isExempt() {
        return this == BOOK || this == FOOD || this == MEDICAL;
    }
}
