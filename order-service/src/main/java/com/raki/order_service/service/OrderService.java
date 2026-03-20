package com.raki.order_service.service;

import com.raki.order_service.client.InventoryClient;
import com.raki.order_service.client.ProductClient;
import com.raki.order_service.client.UserClient;
import com.raki.order_service.model.Order;
import com.raki.order_service.repo.OrderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final UserClient userClient;
    private final InventoryClient client;
    private final ProductClient productClient;
    private final OrderRepo repo;
    public OrderService(OrderRepo repo,InventoryClient client,ProductClient productClient,UserClient userClient){
        this.repo=repo;
        this.client=client;
        this.productClient=productClient;
        this.userClient=userClient;
    }
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = repo.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrderByid(Integer id) {
        Order order = repo.findById(id).get();
        if(order==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    public ResponseEntity<String> createOrder(Order order) {
        Object user = userClient.getUserById(order.getUserId());
        if(user==null){
            order.setStatus("Failed");
            repo.save(order);
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
        Object product = productClient.getProductById(order.getProductId());
        if(product==null){
            order.setStatus("FAILED");
            repo.save(order);
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        }
        Integer stock = client.getStock(order.getProductId());
        if(stock==null){
            order.setStatus("FAILED");
            repo.save(order);
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        }
        if(stock< order.getQuantity()){
            order.setStatus("Failed");
            repo.save(order);
            return new ResponseEntity<>("Not Enough Stock",HttpStatus.BAD_REQUEST);
        }
        client.updateStock(order.getProductId(),-order.getQuantity());
        order.setStatus("CONFIRMED");
        repo.save(order);
        return new ResponseEntity<>("Order Placed Succesfully",HttpStatus.OK);
    }

}
