package tn.amira.application.ports;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.infra.exceptions.ReceiptFormatterException;
import java.util.List;


public interface IReceiptFormatter {

    String formatReceipt(List<ReceiptItem> items) throws ReceiptFormatterException;
}
