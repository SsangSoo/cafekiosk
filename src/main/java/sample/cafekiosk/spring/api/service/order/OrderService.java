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

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTme) {
        List<String> productNumbers = request.getProductNumbers();

        // Product를 찾는다. ProductRepository가 필요하다.
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        // Order 생성
        Order order = Order.create(products, registeredDateTme);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

}
