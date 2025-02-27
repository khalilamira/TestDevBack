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
     * Calcule le montant total des taxes pour un produit donné.
     * <p>
     * La taxe de base est appliquée uniquement si le produit appartient à la catégorie {@link ProductCategory#OTHER}.
     * La taxe d'importation est appliquée si le produit est marqué comme importé.
     * </p>
     *
     * @param product Le produit pour lequel les taxes doivent être calculées.
     * @return Montant total des taxes appliquées, arrondi au multiple de 0,05.
     */
    @Override
    public BigDecimal calculateTax(Product product) {
        BigDecimal taxRate = BigDecimal.ZERO;
        // Appliquer la taxe de base uniquement si le produit n'est pas exempté
        if (product.getCategory() == ProductCategory.OTHER) {
            taxRate = taxRate.add(BASIC_TAX_RATE);
        }
        // Appliquer la taxe d'importation si nécessaire
        if (product.isImported()) {
            taxRate = taxRate.add(IMPORT_DUTY_RATE);
        }
        BigDecimal rawTax = product.getPrice().multiply(taxRate);
        return roundUpToNearestFiveCents(rawTax);
    }

    /**
     * Arrondit un montant donné au multiple de 0,05 le plus proche.
     * <p>
     * Cette méthode est utilisée pour garantir que les taxes sont toujours
     * arrondies conformément aux règles fiscales.
     * </p>
     *
     * @param amount Le montant à arrondir.
     * @return Montant arrondi au multiple de 0,05.
     */
    private BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
        BigDecimal multiplier = new BigDecimal("20");
        return amount.multiply(multiplier)
                .setScale(0, RoundingMode.CEILING)
                .divide(multiplier, 2, RoundingMode.HALF_UP);
    }
}
