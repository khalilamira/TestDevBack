package tn.amira.services.exceptions;

import tn.amira.config.MessageConfig;

/**
 * Exception levée lorsqu'une erreur survient lors du calcul des taxes.
 * <p>
 * Cette exception peut être levée dans plusieurs cas, notamment :
 * <ul>
 *     <li>Une valeur de taxe non définie ou invalide.</li>
 *     <li>Une erreur lors de la récupération des taux de taxe depuis la configuration.</li>
 *     <li>Une erreur de conversion des montants de taxe.</li>
 * </ul>
 *
 *
 * <p>
 * Hérite de {@link RuntimeException} car elle représente une erreur métier
 * qui peut être levée sans gestion obligatoire avec `try-catch`.
 * </p>
 */
public class TaxCalculatorException extends RuntimeException {

    /**
     * Constructeur avec un message personnalisé.
     *
     * @param messageKey Le message détaillant la cause de l'exception.
     */
    public TaxCalculatorException(String messageKey) {
        super(MessageConfig.getMessage(messageKey));
    }

    /**
     * Constructeur avec un message et la cause de l'exception.
     *
     * @param messageKey Le message détaillant la cause de l'exception.
     * @param cause L'exception d'origine.
     */
    public TaxCalculatorException(String messageKey, Throwable cause) {
        super(MessageConfig.getMessage(messageKey), cause);
    }
}

