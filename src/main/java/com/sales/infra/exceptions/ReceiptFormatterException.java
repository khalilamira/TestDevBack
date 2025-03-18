package com.sales.infra.exceptions;

import com.sales.application.config.MessageConfig;


public class ReceiptFormatterException extends RuntimeException {


    public ReceiptFormatterException(String messageKey) {
        super(MessageConfig.getMessage(messageKey));
    }


    public ReceiptFormatterException(String messageKey, Object... args) {
        super(MessageConfig.getMessage(messageKey, args));
    }

    public ReceiptFormatterException(String messageKey, Throwable cause) {
        super(MessageConfig.getMessage(messageKey), cause);
    }
}
