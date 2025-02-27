package tn.amira.services.impls;

import lombok.Getter;
import tn.amira.entities.Product;
import tn.amira.entities.ReceiptItem;
import tn.amira.services.interfaces.ITotalsCalculator;
import tn.amira.services.interfaces.IReceiptFormatter;
import tn.amira.services.interfaces.IReceiptService;
import tn.amira.services.interfaces.ITaxCalculator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du service de gestion des reçus.
 * Ce service permet :
 * <ul>
 *     <li>D'ajouter des produits au reçu</li>
 *     <li>De générer un reçu formaté avec les montants</li>
 * </ul>
 */
@Getter
public class ReceiptServiceImpl implements IReceiptService {
    private final List<ReceiptItem> items = new ArrayList<>();
    private final ITaxCalculator taxCalculator;
    private final ITotalsCalculator totalCalculator;
    private final IReceiptFormatter receiptFormatter;

    /**
     * Constructeur de {@code ReceiptServiceImpl}.
     *
     * @param taxCalculator   Instance du service de calcul des taxes appliquées aux produits.
     * @param receiptFormatter Instance du service de formatage du reçu.
     */
    public ReceiptServiceImpl(ITaxCalculator taxCalculator, IReceiptFormatter receiptFormatter, ITotalsCalculator totalCalculator) {
        this.taxCalculator = taxCalculator;
        this.receiptFormatter = receiptFormatter;
        this.totalCalculator = totalCalculator;
    }

    /**
     * Ajoute un produit au reçu en calculant sa taxe et en l'ajoutant à la liste des éléments du reçu.
     *
     * @param product Le produit à ajouter au reçu.
     */
    public void addProduct(Product product) {
        BigDecimal tax = taxCalculator.calculateTax(product);
        items.add(new ReceiptItem(product, tax));
    }

    /**
     * Génère et retourne le reçu sous forme de texte formaté.
     * Le reçu contient la liste des produits achetés avec leur prix et les taxes appliquées.
     *
     * @return Une chaîne de caractères représentant le reçu formaté.
     */
    public String getReceipt() {
        return receiptFormatter.formatReceipt(List.copyOf(items));
    }
}
