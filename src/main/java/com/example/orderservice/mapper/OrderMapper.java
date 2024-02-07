package com.example.orderservice.mapper;

import com.example.orderservice.dto.Order;
import com.example.orderstatusservice.dto.OrderEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper
{
    OrderEvent orderToEvent(Order order);
}
