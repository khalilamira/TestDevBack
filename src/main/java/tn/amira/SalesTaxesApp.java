package tn.amira;

import tn.amira.services.impls.ReceiptServiceImpl;
import tn.amira.entities.Product;
import tn.amira.entities.enums.ProductCategory;
import tn.amira.services.impls.ReceiptFormatterImpl;
import tn.amira.services.impls.TaxCalculatorImpl;
import tn.amira.services.interfaces.IReceiptFormatter;
import tn.amira.services.interfaces.IReceiptService;
import tn.amira.services.interfaces.ITaxCalculator;

import java.math.BigDecimal;

/**
 * Application principale de gestion des taxes de vente.
 * <p>
 * Cette classe simule un système de calcul des taxes appliquées sur des produits,
 * en fonction de leur catégorie et de leur statut d'importation.
 * </p>
 * <p>
 * Trois scénarios de paniers d'achat sont testés, et le reçu est affiché pour chacun.
 * </p>
 *
 * <h2>Fonctionnalités :</h2>
 * <ul>
 *     <li>Création de produits avec un prix, une catégorie et un statut (importé ou non).</li>
 *     <li>Ajout des produits à un panier d'achat.</li>
 *     <li>Calcul des taxes de vente (10 % sauf pour les produits exemptés).</li>
 *     <li>Application d'une taxe d'importation de 5 % pour les produits importés.</li>
 *     <li>Affichage des reçus avec les montants totaux et les taxes appliquées.</li>
 * </ul>
 *
 */
public class SalesTaxesApp {

    /**
     * Point d'entrée principal de l'application.
     * <p>
     * Ce programme crée et affiche trois scénarios de paniers de produits,
     * en calculant les taxes et en générant des reçus pour chaque panier.
     * </p>
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        // Initialisation des services
        ITaxCalculator taxCalculator = new TaxCalculatorImpl();
        IReceiptFormatter receiptFormatter = new ReceiptFormatterImpl();

        // ===========================
        // 🎯 Scénario 1 : Output 1
        // ===========================
        IReceiptService basket1 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        basket1.addProduct(new Product("book", new BigDecimal("12.49"), false, ProductCategory.BOOK));
        basket1.addProduct(new Product("music CD", new BigDecimal("14.99"), false, ProductCategory.OTHER));
        basket1.addProduct(new Product("chocolate bar", new BigDecimal("0.85"), false, ProductCategory.FOOD));
        System.out.println("Output 1:");
        System.out.println(basket1.getReceipt());

        // ===========================
        // 🎯 Scénario 2 : Output 2
        // ===========================
        IReceiptService basket2 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        basket2.addProduct(new Product("imported box of chocolates", new BigDecimal("10.00"), true, ProductCategory.FOOD));
        basket2.addProduct(new Product("imported bottle of perfume", new BigDecimal("47.50"), true, ProductCategory.OTHER));
        System.out.println("\nOutput 2:");
        System.out.println(basket2.getReceipt());

        // ===========================
        // 🎯 Scénario 3 : Output 3
        // ===========================
        IReceiptService basket3 = new ReceiptServiceImpl(taxCalculator, receiptFormatter);
        basket3.addProduct(new Product("imported bottle of perfume", new BigDecimal("27.99"), true, ProductCategory.OTHER));
        basket3.addProduct(new Product("bottle of perfume", new BigDecimal("18.99"), false, ProductCategory.OTHER));
        basket3.addProduct(new Product("packet of headache pills", new BigDecimal("9.75"), false, ProductCategory.MEDICAL));
        basket3.addProduct(new Product("imported box of chocolates", new BigDecimal("11.25"), true, ProductCategory.FOOD));
        System.out.println("\nOutput 3:");
        System.out.println(basket3.getReceipt());
    }
}
