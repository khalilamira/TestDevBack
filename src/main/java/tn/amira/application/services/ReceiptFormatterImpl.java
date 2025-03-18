package tn.amira.application.services;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.application.ports.ITotalsCalculator;
import tn.amira.application.ports.IReceiptFormatter;
import tn.amira.application.utils.ReceiptUtils;

import java.util.List;

public class ReceiptFormatterImpl implements IReceiptFormatter {

    private final ITotalsCalculator receiptCalculator;
    private final String currencyFormat;

    public ReceiptFormatterImpl(ITotalsCalculator receiptCalculator) {
        this.receiptCalculator = receiptCalculator;
        this.currencyFormat = ReceiptUtils.getCurrencyFormat();
    }

    @Override
    public String formatReceipt(List<ReceiptItem> items) {
        ReceiptUtils.validateItems(items);
        ReceiptSummary summary = receiptCalculator.calculateTotals(items);

        String formattedItems = ReceiptUtils.formatReceiptItems(items);
        String formattedSummary = ReceiptUtils.formatReceiptSummary(summary, currencyFormat);

        return String.join("\n", formattedItems, formattedSummary);
    }
}
