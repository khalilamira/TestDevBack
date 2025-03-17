package tn.amira.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.amira.entities.enums.ProductCategory;
import java.math.BigDecimal;

/**
 * Représente un produit dans le système de taxation.
 * <p>
 * Chaque produit a :
 * <ul>
 *     <li>Un nom {@link #name}.</li>
 *     <li>Un prix {@link #price}.</li>
 *     <li>Un indicateur s'il est importé {@link #imported}.</li>
 *     <li>Une catégorie {@link #category} définissant son type.</li>
 * </ul>
 * <p>
 * Cette classe est immuable et utilise Lombok pour générer automatiquement :
 * <ul>
 *     <li>Les getters ({@code @Getter}).</li>
 *     <li>Une méthode {@code toString()} ({@code @ToString}).</li>
 *     <li>Un constructeur avec tous les paramètres ({@code @RequiredArgsConstructor}).</li>
 * </ul>
 *
 */
@Getter
@ToString
@RequiredArgsConstructor
public class Product {

    /** Nom du produit. */
    private final String name;

    /** Prix du produit (hors taxes). */
    private final BigDecimal price;

    /** Indique si le produit est importé (soumis à des taxes supplémentaires). */
    private final boolean imported;

    /** Catégorie du produit (détermine l'application des taxes). */
    private final ProductCategory category;

}
