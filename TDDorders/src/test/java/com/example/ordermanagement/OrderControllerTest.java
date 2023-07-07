package com.example.ordermanagement;

import com.example.ordermanagement.controller.OrderController;
import com.example.ordermanagement.entity.Order;
import com.example.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order(123L,"Hector G.", LocalDate.now(), "123 Sesame Street", 100.0);
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"Hector G.\",\"orderDate\":\"2023-07-06\",\"shippingAddress\":\"123 Sesame Street\",\"total\":100.0}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Hector G."))
                .andExpect(jsonPath("$.orderDate").value("2023-07-06"))
                .andExpect(jsonPath("$.shippingAddress").value("123 Sesame Street"))
                .andExpect(jsonPath("$.total").value(100.0));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order order1 = new Order(123L,"Hector G.", LocalDate.now(), "123 Sesame Street", 100.0);
        Order order2 = new Order(456L, "Cocomelon Coco", LocalDate.now(), "456 Pbs Drive", 200.0);
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("Hector G."))
                .andExpect(jsonPath("$[0].orderDate").value("2023-07-06"))
                .andExpect(jsonPath("$[0].shippingAddress").value("123 Sesame Street"))
                .andExpect(jsonPath("$[0].total").value(100.0))
                .andExpect(jsonPath("$[1].customerName").value("Cocomelon Coco"))
                .andExpect(jsonPath("$[1].orderDate").value("2023-07-06"))
                .andExpect(jsonPath("$[1].shippingAddress").value("456 Pbs Drive"))
                .andExpect(jsonPath("$[1].total").value(200.0));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order(123L,"Hector G.", LocalDate.now(), "123 Sesame Street", 100.0);
        when(orderService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Hector G."))
                .andExpect(jsonPath("$.orderDate").value("2023-07-06"))
                .andExpect(jsonPath("$.shippingAddress").value("123 Sesame Street"))
                .andExpect(jsonPath("$.total").value(100.0));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order(123L,"Hector G.", LocalDate.now(), "123 Sesame Street", 100.0);
        when(orderService.updateOrder(any(Long.class), any(Order.class))).thenReturn(order);

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"Hector G.\",\"orderDate\":\"2023-07-06\",\"shippingAddress\":\"123 Sesame Street\",\"total\":100.0}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Hector G."))
                .andExpect(jsonPath("$.orderDate").value("2023-07-06"))
                .andExpect(jsonPath("$.shippingAddress").value("123 Sesame Street"))
                .andExpect(jsonPath("$.total").value(100.0));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void testCreateOrder_WithEmptyCustomerName_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"\",\"orderDate\":\"2023-07-06\",\"shippingAddress\":\"123 Sesame street\",\"total\":100.0}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.customerName").value("Customer name is required"));

    }

    @Test
    public void testCreateOrder_WithEmptyShippingAddress_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"Hector G.\",\"orderDate\":\"2023-07-06\",\"shippingAddress\":\"\",\"total\":100.0}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.shippingAddress").value("Shipping address is required"));

    }


}
