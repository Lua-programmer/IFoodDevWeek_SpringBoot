package io.luaprogrammer.ifooddevweek_springboot.service;

import io.luaprogrammer.ifooddevweek_springboot.model.Item;
import io.luaprogrammer.ifooddevweek_springboot.model.ShoppingCart;
import io.luaprogrammer.ifooddevweek_springboot.rest.dto.ItemDto;

public interface ShoppingCartService {
    ShoppingCart viewShoppingCart(Long id);
    ShoppingCart closeShoppingCart(Long id, int formPayment);
    Item includeItemShoppingCart(ItemDto itemDto);
}