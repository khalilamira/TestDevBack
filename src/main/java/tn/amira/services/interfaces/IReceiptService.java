package tn.amira.services.interfaces;

import tn.amira.entities.Product;

public interface IReceiptService {

    void addProduct(Product product);
    void printReceipt();
}
