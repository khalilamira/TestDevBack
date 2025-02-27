📌 SalesTaxesApp

🛒 Introduction

SalesTaxesApp est une application Java permettant de calculer les taxes appliquées à des produits, en fonction de leur catégorie et de leur statut d'importation.
L'application génère des reçus détaillés en affichant les prix après taxes, ainsi que les montants des taxes appliquées.

🚀 Fonctionnalités

📦 Gestion des produits : Ajout de produits avec leur nom, prix, catégorie et statut (importé ou non).

🏷 Calcul automatique des taxes :

10% pour les produits non exemptés (hors livres, nourriture et médicaments).

5% de taxe d'importation pour les produits importés.

📜 Génération d'un reçu détaillé : Affichage des produits avec leur prix final et total des taxes.

✅ Respect des bonnes pratiques de développement (SOLID, Clean Code, TDD).

🛠 Prérequis

Java 21

Maven 3.6+

SonarQube (optionnel pour l'analyse du code)

📖 Documentation Technique

📝 Javadoc

La documentation complète est accessible après exécution de :

mvn javadoc:javadoc

🧪 Tests Unitaires

Le projet inclut des tests unitaires avec JUnit 5. Pour exécuter les tests :

mvn test

🛡️ Qualité du Code (SonarQube)

Le code est analysé avec SonarQube pour garantir le respect des bonnes pratiques.

mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=<TOKEN>