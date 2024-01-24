package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.Product;
import sample.cafekiosk.spring.domain.ProductRepository;
import sample.cafekiosk.spring.domain.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductCreateRequest request) {
        // productNumber
        // 이전까지의 예 001 002 003 004
        // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        // 009 -> 010
        // 11분 21초
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
