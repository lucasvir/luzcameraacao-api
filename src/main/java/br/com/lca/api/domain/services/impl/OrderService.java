package br.com.lca.api.domain.services.impl;

import br.com.lca.api.controllers.exceptions.EmptyResourceException;
import br.com.lca.api.domain.model.Order;
import br.com.lca.api.domain.model.dto.OrderCreateDTO;
import br.com.lca.api.domain.model.dto.OrderDTO;
import br.com.lca.api.domain.model.dto.OrderUpdateDTO;
import br.com.lca.api.domain.model.enums.UnidadeFederativa;
import br.com.lca.api.domain.repositories.OrderRepository;
import br.com.lca.api.domain.services.ServiceStrategy;
import br.com.lca.api.domain.services.validations.VerifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService implements ServiceStrategy<OrderDTO, OrderCreateDTO, OrderUpdateDTO> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VerifyDTO<OrderCreateDTO> verifyCreateDTO;

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        return new OrderDTO(order);
    }

    @Override
    public OrderDTO create(OrderCreateDTO createDTO) {
        verifyCreateDTO.verify(createDTO);

        Order order = new Order(
                createDTO.userId(),
                createDTO.location(),
                createDTO.complement(),
                UnidadeFederativa.fromSigla(createDTO.uf()),
                createDTO.startedAt(),
                createDTO.expiresAt(),
                createDTO.totalValue()
        );

        Order orderEntity = orderRepository.save(order);
        return new OrderDTO(orderEntity);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) throw new EmptyResourceException();

        return orders.stream().map(OrderDTO::new).toList();
    }

    @Override
    public OrderDTO update(Long id, OrderUpdateDTO updateDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        order.updateData(updateDTO);
        Order orderEntity = orderRepository.save(order);

        return new OrderDTO(orderEntity);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id.toString()));

        orderRepository.delete(order);
    }
}
