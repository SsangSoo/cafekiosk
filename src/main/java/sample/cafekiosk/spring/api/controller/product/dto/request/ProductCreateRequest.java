package sample.cafekiosk.spring.api.controller.product.dto.request;

import lombok.Getter;
import sample.cafekiosk.spring.domain.ProductSellingStatus;
import sample.cafekiosk.spring.domain.ProductType;

@Getter
public class ProductCreateRequest {

    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

}
