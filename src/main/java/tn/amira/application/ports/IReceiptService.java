package tn.amira.application.ports;

import tn.amira.domain.model.Product;

public interface IReceiptService {

    void addProduct(Product product);
    void printReceipt();
}

