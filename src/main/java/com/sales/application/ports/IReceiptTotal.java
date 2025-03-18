package com.sales.application.ports;

import com.sales.domain.model.ReceiptItem;
import com.sales.domain.model.ReceiptSummary;
import java.util.List;


public interface IReceiptTotal {

    ReceiptSummary calculateTotals(List<ReceiptItem> items);
}
