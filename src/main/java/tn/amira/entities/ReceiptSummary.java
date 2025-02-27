package tn.amira.entities;

import java.math.BigDecimal;

/**
 * Représente un résumé des montants calculés pour un reçu.
 * <p>
 * Cette classe encapsule les totaux des taxes appliquées ainsi que le montant total du reçu,
 *
 */
public class ReceiptSummary {

    /** Montant total des taxes appliquées sur les produits. */
    private final BigDecimal totalSalesTaxes;

    /** Montant total du reçu, incluant les taxes. */
    private final BigDecimal totalAmount;

    /**
     * Constructeur permettant d'initialiser le résumé d'un reçu.
     *
     * @param totalSalesTaxes Montant total des taxes appliquées.
     * @param totalAmount Montant total à payer incluant les taxes.
     */
    public ReceiptSummary(BigDecimal totalSalesTaxes, BigDecimal totalAmount) {
        this.totalSalesTaxes = totalSalesTaxes;
        this.totalAmount = totalAmount;
    }

    /**
     * Retourne le montant total des taxes appliquées.
     *
     * @return Le total des taxes sous forme de {@link BigDecimal}.
     */
    public BigDecimal getTotalSalesTaxes() {
        return totalSalesTaxes;
    }

    /**
     * Retourne le montant total du reçu, incluant les taxes.
     *
     * @return Le montant total sous forme de {@link BigDecimal}.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}


