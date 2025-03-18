package tn.amira.application.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;


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

    public static String getMessage(String key) {
        return messages.getProperty(key);
    }


    public static String getMessage(String key, Object... args) {
        String message = messages.getProperty(key);
        if (message == null) {
            return "Message non trouvé pour la clé : " + key;
        }
        return MessageFormat.format(message, args);
    }
}
