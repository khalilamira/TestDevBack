package tn.amira.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Classe utilitaire pour charger et récupérer les messages d'erreur.
 * <p>
 * Cette classe charge le fichier {@code messages.properties} et permet d'accéder
 * aux messages d'erreur via une clé.
 * </p>
 */
public class MessageConfig {

    private static final Properties messages = new Properties();

    private MessageConfig(){
    }

    static {
        try (InputStream input = MessageConfig.class.getClassLoader().getResourceAsStream("messages.properties")) {
            if (input == null) {
                throw new RuntimeException("Fichier messages.properties introuvable !");
            }
            messages.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier messages.properties", e);
        }
    }

    /**
     * Récupère un message d'erreur à partir de sa clé.
     *
     * @param key Clé du message dans le fichier properties.
     * @return Le message correspondant ou {@code null} si la clé n'existe pas.
     */
    public static String getMessage(String key) {
        return messages.getProperty(key);
    }

    /**
     * Récupère un message d'erreur avec des paramètres dynamiques.
     *
     * @param key Clé du message dans le fichier properties.
     * @param args Valeurs à insérer dans le message.
     * @return Le message formaté avec les paramètres.
     */
    public static String getMessage(String key, Object... args) {
        String message = messages.getProperty(key);
        if (message == null) {
            return "Message non trouvé pour la clé : " + key;
        }
        return MessageFormat.format(message, args);
    }
}
