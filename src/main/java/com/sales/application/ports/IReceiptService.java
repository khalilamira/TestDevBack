package com.sales.application.ports;

import com.sales.domain.model.Product;

public interface IReceiptService {

    void addProduct(Product product);
    void printReceipt();
}

