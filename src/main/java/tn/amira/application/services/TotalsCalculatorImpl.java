package tn.amira.application.services;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.application.ports.ITotalsCalculator;
import tn.amira.application.utils.ReceiptCalculationUtils;

import java.util.List;

public class TotalsCalculatorImpl implements ITotalsCalculator {

    @Override
    public ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        return ReceiptCalculationUtils.calculateTotals(items);
    }
}
