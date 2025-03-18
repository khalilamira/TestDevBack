## 💡 Remarques et Améliorations Possibles

### 1️⃣ **Gestion des quantités des produits**
Lors de l'implémentation, une question s'est posée : **Une ligne dans le reçu représente-t-elle toujours un seul produit ou faut-il gérer une quantité ?**  
Actuellement, le modèle ne prend en compte **qu'un seul produit par ligne**, mais il serait préférable d'ajouter un **attribut `quantity`** dans la classe `ReceiptItem`.  
Cela permettrait :
- De gérer **plusieurs unités du même produit** sans dupliquer les entrées.
- D'optimiser les calculs des taxes et totaux.


## 💡 Améliorations Possibles

- **Ajout de la Javadoc** :  
  Il serait préférable d'ajouter des **commentaires Javadoc** à toutes les classes et méthodes publiques pour améliorer la lisibilité et la maintenabilité du code.  
  Cela permettrait :
  - De **mieux documenter** le rôle de chaque classe.
  - De **faciliter la compréhension** pour les autres développeurs.
  - D'améliorer la **maintenabilité du projet**.

  **Exemple de Javadoc à ajouter :**
  ```java
  /**
   * Service qui formate le reçu avant affichage.
   * Il génère une représentation textuelle des produits achetés et des totaux.
   */
  public class ReceiptFormatterService {
      /**
       * Formate le reçu à partir de la liste des articles.
       * @param items Liste des articles du reçu
       * @return Chaîne formatée du reçu
       */
      public String formatReceipt(List<ReceiptItem> items) { ... }
  }
