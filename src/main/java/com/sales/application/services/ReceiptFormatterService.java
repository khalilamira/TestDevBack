package com.sales.application.services;

import com.sales.domain.model.ReceiptItem;
import com.sales.domain.model.ReceiptSummary;
import com.sales.application.ports.IReceiptTotal;
import com.sales.application.ports.IReceiptFormatter;
import com.sales.application.utils.ReceiptFormattingUtils;

import java.util.List;

public class ReceiptFormatterService implements IReceiptFormatter {

    private final IReceiptTotal receiptCalculator;
    private final String currencyFormat;

    public ReceiptFormatterService(IReceiptTotal receiptCalculator) {
        this.receiptCalculator = receiptCalculator;
        this.currencyFormat = ReceiptFormattingUtils.getCurrencyFormat();
    }

    @Override
    public String formatReceipt(List<ReceiptItem> items) {
        ReceiptFormattingUtils.validateItems(items);
        ReceiptSummary summary = receiptCalculator.calculateTotals(items);

        String formattedItems = ReceiptFormattingUtils.formatReceiptItems(items);
        String formattedSummary = ReceiptFormattingUtils.formatReceiptSummary(summary, currencyFormat);

        return String.join("\n", formattedItems, formattedSummary);
    }
}
