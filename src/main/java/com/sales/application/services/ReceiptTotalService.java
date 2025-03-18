package com.sales.application.services;

import com.sales.application.utils.ReceiptCalculationUtils;
import com.sales.domain.model.ReceiptItem;
import com.sales.domain.model.ReceiptSummary;
import com.sales.application.ports.IReceiptTotal;

import java.util.List;

public class ReceiptTotalService implements IReceiptTotal {

    @Override
    public ReceiptSummary calculateTotals(List<ReceiptItem> items) {
        return ReceiptCalculationUtils.calculateTotals(items);
    }
}
