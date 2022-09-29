package io.luaprogrammer.ifooddevweek_springboot.repository;

import io.luaprogrammer.ifooddevweek_springboot.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}