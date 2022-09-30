package io.luaprogrammer.ifooddevweek_springboot.rest.resource;

import io.luaprogrammer.ifooddevweek_springboot.model.Item;
import io.luaprogrammer.ifooddevweek_springboot.model.ShoppingCart;
import io.luaprogrammer.ifooddevweek_springboot.rest.dto.ItemDto;
import io.luaprogrammer.ifooddevweek_springboot.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ifood-devweek/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartResource {

    private final ShoppingCartService service;

    @PostMapping
    public Item includeItemShoppingCart(@RequestBody ItemDto itemDto) {
        return service.includeItemShoppingCart(itemDto);
    }

    @GetMapping("/{id}")
    public ShoppingCart viewShoppingCart(@PathVariable("id") Long id) {
        return service.viewShoppingCart(id);
    }

    @PatchMapping("/closeShoppingCart/{shoppingCartId}")
    public ShoppingCart closeShoppingCart(@PathVariable("shoppingCartId") Long shoppingCartId,
                                          @RequestParam("formPayment") int FormPayment)
    {
        return service.closeShoppingCart(shoppingCartId, FormPayment);
    }
}
