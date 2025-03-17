package tn.amira.services.impls;

import tn.amira.config.MessageConfig;
import tn.amira.entities.ReceiptItem;
import tn.amira.entities.ReceiptSummary;
import tn.amira.services.exceptions.ReceiptFormatterException;
import tn.amira.services.interfaces.ITotalsCalculator;
import tn.amira.services.interfaces.IReceiptFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ReceiptFormatterImpl implements IReceiptFormatter {

    private final ITotalsCalculator receiptCalculator;
    private final String currencyFormat;

    public ReceiptFormatterImpl(ITotalsCalculator receiptCalculator, String currencyFormat) {
        this.receiptCalculator = receiptCalculator;
        this.currencyFormat = currencyFormat;
    }

    @Override
    public String formatReceipt(List<ReceiptItem> items) {
        validateItems(items);
        ReceiptSummary summary = receiptCalculator.calculateTotals(items);
        return generateFormattedReceipt(items, summary);
    }

    private void validateItems(List<ReceiptItem> items) {
        if (items == null || items.isEmpty()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.emptyList"));
        }
    }


    private String generateFormattedReceipt(List<ReceiptItem> items, ReceiptSummary summary) {
        if (Objects.isNull(currencyFormat) || currencyFormat.isBlank()) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.invalidFormat"));
        }

        String formattedItems = formatItems(items);
        return formattedItems + "\n" + formatSummary(summary);
    }


    private String formatItems(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::format)
                .collect(Collectors.joining("\n"));
    }


    private String formatSummary(ReceiptSummary summary) {
        try {
            return String.format(currencyFormat, summary.getTotalSalesTaxes(), summary.getTotalAmount());
        } catch (Exception e) {
            throw new ReceiptFormatterException(MessageConfig.getMessage("error.receipt.formatError"), e);
        }
    }
}
