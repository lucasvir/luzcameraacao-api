package br.com.lca.api.controllers;

import br.com.lca.api.domain.model.dto.OrderCreateDTO;
import br.com.lca.api.domain.model.dto.OrderDTO;
import br.com.lca.api.domain.model.dto.OrderUpdateDTO;
import br.com.lca.api.services.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO createDTO) {
        OrderDTO order = orderService.create(createDTO);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.id()).toUri();

        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> show(@PathVariable Long id) {
        OrderDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody OrderUpdateDTO updateDTO){
        OrderDTO order = orderService.update(id, updateDTO);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
