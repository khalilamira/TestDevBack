package tn.amira.application.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyConfig {

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

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    /**
     * Permet de modifier une propriété (utile pour les tests).
     * @param key Clé de la propriété
     * @param value Valeur de la propriété
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

}

