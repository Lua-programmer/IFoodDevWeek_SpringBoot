package io.luaprogrammer.ifooddevweek_springboot.service.Impl;

import io.luaprogrammer.ifooddevweek_springboot.enumeration.FormPayment;
import io.luaprogrammer.ifooddevweek_springboot.model.Item;
import io.luaprogrammer.ifooddevweek_springboot.model.Restaurant;
import io.luaprogrammer.ifooddevweek_springboot.model.ShoppingCart;
import io.luaprogrammer.ifooddevweek_springboot.repository.ItemRepository;
import io.luaprogrammer.ifooddevweek_springboot.repository.ProductRepository;
import io.luaprogrammer.ifooddevweek_springboot.repository.ShoppingCartRepository;
import io.luaprogrammer.ifooddevweek_springboot.rest.dto.ItemDto;
import io.luaprogrammer.ifooddevweek_springboot.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartImpl implements ShoppingCartService {

    private final ShoppingCartRepository repository;
    private final ProductRepository productRepository;

    private final ItemRepository itemRepository;

    @Override
    public ShoppingCart viewShoppingCart(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
                    throw new RuntimeException("Couldn't find shopping cart with id " + id);
                }
        );
    }

    @Override
    public ShoppingCart closeShoppingCart(Integer id, int formPayment) {
        ShoppingCart shoppingCart = viewShoppingCart(id);

        if (shoppingCart.getItems().isEmpty()) throw new RuntimeException("Include items in shopping cart");

        FormPayment payment = switch (formPayment) {
            case 0 -> FormPayment.MONEY;
            case 1 -> FormPayment.BOARDMACHINE;
            case 3 -> FormPayment.PIX;
            default -> throw new RuntimeException("FormPayment invalid");
        };

        shoppingCart.setFormPayment(payment);
        shoppingCart.setClosed(true);

        return repository.save(shoppingCart);
    }

    @Override
    public Item includeItemShoppingCart(ItemDto itemDto) {
        ShoppingCart shoppingCart = viewShoppingCart(itemDto.getShoppingCartId());

        if (shoppingCart.isClosed()) {
            throw new RuntimeException("This shopping cart is closed");
        }

        Item insertItem = Item.builder()
                .quantity(itemDto.getQuantity())
                .shoppingCart(shoppingCart)
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(() ->
                {
                    throw new RuntimeException("Product not found");
                })).build();

        List<Item> shoppingCartItems = shoppingCart.getItems();
        if (shoppingCartItems.isEmpty()) {
            shoppingCartItems.add(insertItem);
        } else {
            Restaurant currentRestaurant = shoppingCartItems.get(0).getProduct().getRestaurant();
            Restaurant restaurantToAddItem = insertItem.getProduct().getRestaurant();
            if (currentRestaurant.equals(restaurantToAddItem)) {
                shoppingCartItems.add(insertItem);
            } else {
                throw new RuntimeException("It's not possible to add products from different restaurants. Close the shopping cart or empty it.");
            }
        }

        List<Double> itemsValues = new ArrayList<Double>();
        for (Item shoppingCartItem : shoppingCartItems) {
            double totalValue = shoppingCartItem.getProduct().getUnitPrice() * shoppingCartItem.getQuantity();
            itemsValues.add(totalValue);
        }

        double totalShoppingCartValue = itemsValues.stream().mapToDouble(value -> value).sum();
        shoppingCart.setTotalValue(totalShoppingCartValue);

        repository.save(shoppingCart);
        return insertItem;
    }
}
