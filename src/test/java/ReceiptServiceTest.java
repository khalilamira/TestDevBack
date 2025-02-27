import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.amira.config.PropertyConfig;
import tn.amira.entities.Product;
import tn.amira.entities.enums.ProductCategory;
import tn.amira.services.impls.TotalsCalculatorImpl;
import tn.amira.services.impls.ReceiptServiceImpl;
import tn.amira.services.impls.ReceiptFormatterImpl;
import tn.amira.services.impls.TaxCalculatorImpl;
import tn.amira.services.interfaces.ITotalsCalculator;
import tn.amira.services.interfaces.IReceiptFormatter;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test unitaire simplifié pour vérifier si le total des produits du reçu est correctement calculé.
 * <p>
 * Ce test :
 * <ul>
 *     <li>Ajoute plusieurs produits au panier.</li>
 *     <li>Calcule directement le total via une méthode dédiée.</li>
 *     <li>Compare la valeur obtenue avec la valeur attendue.</li>
 * </ul>
 * </p>
 */
class ReceiptServiceTest {

    private ReceiptServiceImpl receiptService;

    /**
     * Initialise les services avant chaque test.
     * <p>
     * Crée une nouvelle instance de {@link ReceiptServiceImpl}
     * avec un calculateur de taxes et un formateur de reçus.
     * </p>
     */
    @BeforeEach
    public void setup() {
        String currencyFormat = PropertyConfig.getProperty("receipt.format.summary");
        ITotalsCalculator receiptCalculator = new TotalsCalculatorImpl();
        IReceiptFormatter receiptFormatter = new ReceiptFormatterImpl(receiptCalculator, currencyFormat);
        receiptService = new ReceiptServiceImpl(new TaxCalculatorImpl(), receiptFormatter,receiptCalculator);
    }

    /**
     * Vérifie si le total des produits du panier est correctement calculé
     *
     * <p>
     * Scénario testé :
     * <ul>
     *     <li>Ajout de 3 produits au panier.</li>
     *     <li>Calcul du total attendu (avec taxes).</li>
     *     <li>Comparaison entre le total attendu et le total réel.</li>
     * </ul>
     * </p>
     */
    @Test
    void testTotalAmountDirectly() {
        // Given - Ajout des produits au panier
        receiptService.addProduct(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK));
        receiptService.addProduct(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER));
        receiptService.addProduct(new Product("chocolate bar", new BigDecimal("0.85"), false, ProductCategory.FOOD));

        // When - Calcul du total attendu
        BigDecimal expectedTotal = new BigDecimal("29.83"); // Total attendu avec taxes
        BigDecimal actualTotal = receiptService.getTotalCalculator().calculateTotals(receiptService.getItems()).getTotalAmount();// Méthode qui retourne le total calculé

        // Then - Vérification du total
        assertEquals(expectedTotal, actualTotal, "Le total calculé est incorrect.");
    }
}
