package com.sales.application.ports;

import com.sales.domain.model.ReceiptItem;
import com.sales.infra.exceptions.ReceiptFormatterException;
import java.util.List;


public interface IReceiptFormatter {

    String formatReceipt(List<ReceiptItem> items) throws ReceiptFormatterException;
}
