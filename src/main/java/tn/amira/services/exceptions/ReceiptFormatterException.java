package tn.amira.services.exceptions;

import tn.amira.config.MessageConfig;

/**
 * Exception levée lorsqu'une erreur survient lors de la génération du reçu.
 * <p>
 * Cette exception est levée dans plusieurs cas, notamment :
 * <ul>
 *     <li>Si la liste des articles du reçu est vide.</li>
 *     <li>Si le format du reçu est invalide ou non défini.</li>
 *     <li>Si une erreur survient lors du formatage du reçu.</li>
 * </ul>
 *
 *
 * <p>
 * Les messages d'erreur sont définis dans {@code messages.properties} et récupérés via {@link MessageConfig}.
 * </p>
 */
public class ReceiptFormatterException extends RuntimeException {

    /**
     * Constructeur avec une clé de message d'erreur.
     *
     * @param messageKey La clé du message d'erreur définie dans {@code messages.properties}.
     */
    public ReceiptFormatterException(String messageKey) {
        super(MessageConfig.getMessage(messageKey));
    }

    /**
     * Constructeur avec une clé de message d'erreur et des paramètres dynamiques.
     *
     * @param messageKey La clé du message d'erreur définie dans {@code messages.properties}.
     * @param args Valeurs dynamiques à insérer dans le message.
     */
    public ReceiptFormatterException(String messageKey, Object... args) {
        super(MessageConfig.getMessage(messageKey, args));
    }

    /**
     * Constructeur avec une clé de message d'erreur et une cause.
     *
     * @param messageKey La clé du message d'erreur définie dans {@code messages.properties}.
     * @param cause L'exception d'origine à inclure.
     */
    public ReceiptFormatterException(String messageKey, Throwable cause) {
        super(MessageConfig.getMessage(messageKey), cause);
    }
}
