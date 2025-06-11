package com.example.goblidas_backend.services;

import com.example.goblidas_backend.DTOs.CartItemDTO;
import com.example.goblidas_backend.DTOs.CreateOrderDTO;
import com.example.goblidas_backend.entities.*;
import com.example.goblidas_backend.entities.enums.OrderStatus;
import com.example.goblidas_backend.repositories.DetailRepository;
import com.example.goblidas_backend.repositories.OrderRepository;
import com.example.goblidas_backend.repositories.UserAdressRepository;
import com.example.goblidas_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserAdressRepository userAdressRepository;

    @Autowired
    private DetailRepository detailRepository;

    public OrderService(JpaRepository<Order, Long> baseRepository, UserAdressRepository userAdressRepository, OrderRepository orderRepository,  DetailRepository detailRepository){
        super(baseRepository);
        this.userAdressRepository = userAdressRepository;
        this.detailRepository = detailRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrderFromCart(CreateOrderDTO createOrderDTO) {
        System.out.println("Creando orden con DTO: " + createOrderDTO);
        
        UserAdress userAdress = userAdressRepository.findById(createOrderDTO.getUserAdressId())
                .orElseThrow(() -> new RuntimeException("Direccion de usuario no encontrada"));
        System.out.println("Dirección encontrada: " + userAdress);

        // Verificar que la dirección pertenece al usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        System.out.println("Usuario autenticado: " + authenticatedUser);
        
        if (!userAdress.getUserId().getId().equals(authenticatedUser.getId())) {
            throw new RuntimeException("La dirección no pertenece al usuario autenticado");
        }

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setUserAdressId(userAdress);
        order.setOrderStatus(OrderStatus.PENDING);

        List<OrderDetail> orderDetails = new ArrayList<>();
        float total = 0f;

        for (CartItemDTO item: createOrderDTO.getCartItems()) {
            System.out.println("Procesando item: " + item);
            Detail detail = detailRepository.findById(item.getDetailId())
                    .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
            System.out.println("Detalle encontrado: " + detail);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order);
            orderDetail.setDetailId(detail);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setUnitPrice(item.getPrice());

            OrderDetailId id = new OrderDetailId();
            id.setOrderId(order.getId());
            id.setDetailId(detail.getId());
            orderDetail.setId(id);

            orderDetails.add(orderDetail);

            total += item.getQuantity() * item.getPrice().intValue();
            if (detail.getStock() <= 0) {
                detail.setActive(false);
            }
            detail.setStock(detail.getStock() - item.getQuantity());
        }

        order.setSummary(total);
        order.setOrderDetails(orderDetails);

        Order savedOrder = orderRepository.save(order);
        System.out.println("Orden guardada: " + savedOrder);
        return savedOrder;
    }

    @Transactional
    public void revertStock(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Orden no encontrada para revertir stock"));

        for (OrderDetail detail : order.getOrderDetails()){
            Detail productDetail = detail.getDetailId();

            productDetail.setStock(productDetail.getStock() + detail.getQuantity());

            if(!productDetail.getActive() && productDetail.getStock() > 0){
                productDetail.setActive(true);
            }

            detailRepository.save(productDetail);

        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Transactional
    public void markOrderAsPaid(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Orden no encontrada para marcala como pagada"));

        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        System.out.println("Buscando órdenes para usuario: " + userId);
        List<Order> orders = orderRepository.findByUserAdressIdUserId_Id(userId);
        System.out.println("Órdenes encontradas: " + orders);
        return orders;
    }
}
