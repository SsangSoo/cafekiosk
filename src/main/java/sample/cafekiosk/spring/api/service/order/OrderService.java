package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.controller.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.Product;
import sample.cafekiosk.spring.domain.ProductRepository;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTme) {
        List<String> productNumbers = request.getProductNumbers();

        List<Product> duplicateProducts = findProductsBy(productNumbers);

        // Order 생성
        Order order = Order.create(duplicateProducts, registeredDateTme);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        // Product를 찾는다. ProductRepository가 필요하다.
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        // 프로덕트 넘버에 맞는 제품들을 가지고 온다.
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        // productNumbers 를 순회하면서 제품번호에 맞는 객체들을 List로 뽑아낸다.
        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }

}
