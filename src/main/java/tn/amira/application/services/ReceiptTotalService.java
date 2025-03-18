package tn.amira.application.services;

import tn.amira.application.utils.ReceiptCalculationUtils;
import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import tn.amira.application.ports.IReceiptTotal;

import java.util.List;

public class ReceiptTotalService implements IReceiptTotal {

    @Override
    public ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        return ReceiptCalculationUtils.calculateTotals(items);
    }
}
