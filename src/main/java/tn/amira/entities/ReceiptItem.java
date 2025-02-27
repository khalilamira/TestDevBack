package tn.amira.entities;

import lombok.Getter;
import tn.amira.config.PropertyConfig;
import java.math.BigDecimal;

/**
 * Représente une ligne d'un reçu pour un produit donné.
 * <p>
 * Cette classe encapsule :
 * <ul>
 *     <li>Le produit concerné {@link #product}.</li>
 *     <li>Le montant de la taxe appliquée {@link #taxAmount}.</li>
 *     <li>Le prix total du produit (prix initial + taxe) {@link #totalPrice}.</li>
 * </ul>
 * <p>
 * Le format de la ligne du reçu est défini par la propriété {@code receipt.format.line}
 * dans {@link PropertyConfig}.
 * </p>
 */
public class ReceiptItem {

    /** Produit concerné par cette ligne du reçu. */
    private final Product product;

    /** Montant des taxes appliquées sur le produit. */
    @Getter
    private final BigDecimal taxAmount;

    /** Prix total du produit après application des taxes. */
    @Getter
    private final BigDecimal totalPrice;

    /** Format de la ligne du reçu, récupéré depuis {@link PropertyConfig}. */
    private static final String LINE_FORMAT = PropertyConfig.getProperty("receipt.format.line");

    /**
     * Constructeur d'un élément du reçu.
     * <p>
     * Le prix total est calculé en ajoutant le montant de la taxe au prix initial du produit.
     * </p>
     *
     * @param product   Le produit associé à cette ligne du reçu.
     * @param taxAmount Le montant des taxes appliquées au produit.
     */
    public ReceiptItem(Product product, BigDecimal taxAmount) {
        this.product = product;
        this.taxAmount = taxAmount;
        this.totalPrice = product.getPrice().add(taxAmount);
    }

    /**
     * Formate cette ligne du reçu en une chaîne de caractères.
     * <p>
     * Le format est défini par {@link #LINE_FORMAT} et permet d'afficher
     * le produit et son prix total de manière structurée.
     * </p>
     *
     * @return Une chaîne représentant cette ligne du reçu.
     */
    public String format() {
        return String.format(LINE_FORMAT, product.getName(), totalPrice);
    }
}