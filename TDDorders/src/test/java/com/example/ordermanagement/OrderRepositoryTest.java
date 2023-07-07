package com.example.ordermanagement;

import com.example.ordermanagement.entity.Order;
import com.example.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void saveOrder() {
        Order order = new Order();
        order.setCustomerName("Hector Gonzalez");
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress("123 Sesame Street");
        order.setTotal(20.0);


        Order saved = repository.save(order);
        Optional<Order> found = repository.findById(saved.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(saved);
    }

    @Test
    public void findOrder() {
        Order order = new Order();
        order.setCustomerName("Hector Gonzalez");
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress("123 Sesame Street");
        order.setTotal(20.0);


        Order saved = repository.save(order);

        Optional<Order> found = repository.findById(saved.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(saved);
    }

    @Test
    public void updateOrder() {
        Order order = new Order();
        order.setCustomerName("Hector Gonzalez");
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress("123 Sesame Street");
        order.setTotal(150.0);


        Order saved = repository.save(order);

        saved.setTotal(275.0);
        repository.save(saved);

        Optional<Order> found = repository.findById(saved.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getTotal()).isEqualTo(275.0);
    }

    @Test
    public void deleteOrder() {
        Order order = new Order();
        order.setCustomerName("Hector Gonzalez");
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress("123 Sesame Street");
        order.setTotal(100.0);


        Order saved = repository.save(order);

        repository.deleteById(saved.getId());

        Optional<Order> found = repository.findById(saved.getId());

        assertThat(found.isPresent()).isFalse();
    }
}
