package tn.amira.services.impls;

import tn.amira.config.PropertyConfig;
import tn.amira.entities.Product;
import tn.amira.entities.enums.ProductCategory;
import tn.amira.services.interfaces.ITaxCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implémentation du service de calcul des taxes appliquées aux produits.
 * <p>
 * Cette classe applique :
 * <ul>
 *     <li>Une taxe de base pour les produits non exemptés.</li>
 *     <li>Une taxe d'importation pour les produits importés.</li>
 *     <li>Un arrondi au multiple de 0,05 pour le calcul des taxes.</li>
 * </ul>
 * <p>
 * Les taux de taxation sont configurés via {@link PropertyConfig} et sont récupérés dynamiquement.
 * </p>
 */
public class TaxCalculatorImpl implements ITaxCalculator {

    private static final BigDecimal BASIC_TAX_RATE = BigDecimal.valueOf(PropertyConfig.getDoubleProperty("tax.basic.rate"));
    private static final BigDecimal IMPORT_DUTY_RATE = BigDecimal.valueOf(PropertyConfig.getDoubleProperty("tax.import.rate"));

    /**
     * Calcule le montant de la taxe applicable à un produit donné.
     * <p>
     * Cette méthode détermine la taxe de base et la taxe d'importation applicables,
     * puis applique un arrondi au multiple de 0,05 le plus proche.
     * </p>
     *
     * @param product Le produit pour lequel la taxe est calculée.
     * @return Le montant total de la taxe appliquée au produit, arrondi.
     */
    @Override
    public BigDecimal calculateTax(Product product) {
        BigDecimal taxRate = getApplicableTaxRate(product).add(getImportTaxRate(product));
        return roundPrice(product.getPrice().multiply(taxRate));
    }

    /**
     * Détermine le taux de taxe applicable en fonction de la catégorie du produit.
     * <p>
     * Les produits classés comme "OTHER" sont soumis à une taxe de base,
     * tandis que les livres, la nourriture et les produits médicaux en sont exemptés.
     * </p>
     *
     * @param product Le produit concerné.
     * @return Le taux de taxe de base applicable.
     */
    private BigDecimal getApplicableTaxRate(Product product) {
        return product.getCategory() == ProductCategory.OTHER ? BASIC_TAX_RATE : BigDecimal.ZERO;
    }

    /**
     * Détermine la taxe d'importation si le produit est importé.
     * <p>
     * Tous les produits importés sont soumis à une taxe d'importation supplémentaire de 5 %.
     * </p>
     *
     * @param product Le produit concerné.
     * @return Le taux de taxe d'importation applicable.
     */
    private BigDecimal getImportTaxRate(Product product) {
        return product.isImported() ? IMPORT_DUTY_RATE : BigDecimal.ZERO;
    }

    /**
     * Arrondit un montant au multiple de 0,05 le plus proche
     * <p>
     * Cette méthode :
     * <ul>
     *     <li>Divise le montant par 0,05 pour obtenir un facteur d’arrondi.</li>
     *     <li>Arrondit ce facteur au plafond.</li>
     *     <li>Re-multiplie par 0,05 pour revenir au montant arrondi.</li>
     * </ul>
     * </p>
     *
     * @param amount Le montant à arrondir.
     * @return Le montant arrondi au multiple de 0,05.
     */
    private BigDecimal roundPrice(BigDecimal amount) {
        final BigDecimal rounding = BigDecimal.valueOf(IMPORT_DUTY_RATE.doubleValue());

        return amount.divide(rounding, 0, RoundingMode.CEILING)
                .multiply(rounding);
    }


}
