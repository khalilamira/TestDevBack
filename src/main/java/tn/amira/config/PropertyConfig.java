package tn.amira.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe utilitaire pour charger et récupérer les configurations depuis un fichier de propriétés.
 * <p>
 * Cette classe charge automatiquement le fichier {@code application.properties}
 * situé dans le dossier {@code resources} et fournit des méthodes utilitaires
 * pour récupérer les valeurs de configuration.
 * </p>
 *
 * <p>Elle permet de récupérer des valeurs sous forme de :</p>
 * <ul>
 *     <li>{@code String} via {@link #getProperty(String)}</li>
 *     <li>{@code double} via {@link #getDoubleProperty(String)}</li>
 * </ul>
 *
 * <p>
 * Si le fichier de configuration n'est pas trouvé ou s'il y a une erreur de chargement,
 * une exception {@link RuntimeException} est levée.
 * </p>
 */
public class PropertyConfig {

    /** Instance unique de {@link Properties} contenant les configurations chargées. */
    private static final Properties properties = new Properties();

    private PropertyConfig(){
    }

    static {
        try (InputStream input = PropertyConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Fichier application.properties introuvable dans resources !");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier application.properties", e);
        }
    }

    /**
     * Récupère la valeur d'une propriété sous forme de chaîne de caractères.
     *
     * @param key La clé de la propriété à récupérer.
     * @return La valeur de la propriété sous forme de chaîne, ou {@code null} si elle n'existe pas.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Récupère la valeur d'une propriété sous forme de {@code double}.
     * <p>
     * Cette méthode utilise {@link #getProperty(String)} et convertit la valeur en {@code double}.
     * </p>
     *
     * @param key La clé de la propriété à récupérer.
     * @return La valeur de la propriété convertie en {@code double}.
     * @throws NumberFormatException Si la valeur de la propriété ne peut pas être convertie en nombre.
     */
    public static double getDoubleProperty(String key) {
        return Double.parseDouble(getProperty(key));
    }
}

