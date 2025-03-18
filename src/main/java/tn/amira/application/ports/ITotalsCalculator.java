package tn.amira.application.ports;

import tn.amira.domain.model.ReceiptItem;
import tn.amira.domain.model.ReceiptSummary;
import java.util.List;


public interface ITotalsCalculator {

    ReceiptSummary calculateTotals(List<ReceiptItem> items);
}
