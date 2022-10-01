package io.luaprogrammer.ifooddevweek_springboot.service;

import io.luaprogrammer.ifooddevweek_springboot.model.Item;
import io.luaprogrammer.ifooddevweek_springboot.model.ShoppingCart;
import io.luaprogrammer.ifooddevweek_springboot.rest.dto.ItemDto;

public interface ShoppingCartService {
    ShoppingCart viewShoppingCart(Integer id);
    ShoppingCart closeShoppingCart(Integer id, int formPayment);
    Item includeItemShoppingCart(ItemDto itemDto);
}