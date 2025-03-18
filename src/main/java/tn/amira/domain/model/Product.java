package tn.amira.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.amira.domain.enums.ProductCategory;
import java.math.BigDecimal;

@Getter
@ToString
@RequiredArgsConstructor
public class Product {

    private final String name;
    private final BigDecimal price;
    private final boolean imported;
    private final ProductCategory category;

}
