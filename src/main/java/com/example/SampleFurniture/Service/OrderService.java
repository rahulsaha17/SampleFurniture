package com.example.SampleFurniture.Service;

import com.example.SampleFurniture.DTO.OrderRequest;
import com.example.SampleFurniture.Entity.Order;
import com.example.SampleFurniture.Entity.Product;
import com.example.SampleFurniture.Repository.OrderRepository;
import com.example.SampleFurniture.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order saveOrder(OrderRequest request) {
        Order order = new Order();

        if (request.getProductId() != null) {
            // Order from stock
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < request.getQuantity()) {
                throw new RuntimeException("Not enough stock available!");
            }

            product.setStock(product.getStock() - request.getQuantity()); // Reduce stock
            productRepository.save(product);

            order.setProduct(product);
            order.setTotalPrice(product.getPrice() * request.getQuantity());
        } else {
            // Custom Order
            order.setCustomProductName(request.getCustomProductName());
            order.setCustomProductDescription(request.getCustomProductDescription());
            order.setCustomProductPrice(request.getCustomProductPrice());
            order.setTotalPrice(request.getCustomProductPrice() * request.getQuantity());
        }

        order.setQuantity(request.getQuantity());
        order.setCustomerName(request.getCustomerName());
        order.setOrderStatus("PENDING"); // Default status
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

