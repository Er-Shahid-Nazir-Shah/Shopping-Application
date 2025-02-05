package com.example.shoppy.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.shoppy.dto.OrderDTO;
import com.example.shoppy.dto.UserSessionDTO;
import com.example.shoppy.service.OrderService;

@Controller
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserSessionDTO userSessionDTO;

    @GetMapping("/orderCancel/{orderId}")
    public String getOrderCancelled(@PathVariable("orderId") int orderId, Model model) {
        logger.info("Processing cancellation request for order ID: " + orderId);

        OrderDTO orderDTO = orderService.cancelOrder(orderId);
        model.addAttribute("order", orderDTO);
        model.addAttribute("isLoggedIn", userSessionDTO.isLoggedIn());
        model.addAttribute("username", userSessionDTO.getUsername());
        return "orderCancel";
    }

    @GetMapping("/orderCreate/{customerId}")
    public String createOrder(@PathVariable("customerId") int customerId, Model model) {
        logger.info("Processing order creation request for customer ID: " + customerId);

        orderService.createOrder(customerId);
        // model.addAttribute("order", orderDTO);
        model.addAttribute("isLoggedIn", userSessionDTO.isLoggedIn());
        model.addAttribute("username", userSessionDTO.getUsername());
        return "orderSuccess";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        int userId = userSessionDTO.getUserId(); // Example, replace with actual user identification logic
        logger.info("Fetching orders for customer ID: " + userId);

        List<OrderDTO> orders = orderService.getCustomerOrders(userId);
        model.addAttribute("orders", orders);
        model.addAttribute("isLoggedIn", userSessionDTO.isLoggedIn());
        model.addAttribute("username", userSessionDTO.getUsername());
        return "orders";
    }

    @GetMapping("/order/details/{orderId}")
    public String getOrderDetails(Model model, @PathVariable("orderId") int orderId) {
        logger.info("Fetching details for order ID: " + orderId);

        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        model.addAttribute("order", orderDTO);
        model.addAttribute("orderItems", orderDTO.getOrderItems());
        model.addAttribute("isLoggedIn", userSessionDTO.isLoggedIn());
        model.addAttribute("username", userSessionDTO.getUsername());
        return "orderDetails";
    }
}

