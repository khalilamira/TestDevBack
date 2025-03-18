package tn.amira.application.services;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.application.ports.IReceiptTotal;
import tn.amira.application.ports.IReceiptFormatter;
import tn.amira.application.utils.ReceiptFormattingUtils;

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
