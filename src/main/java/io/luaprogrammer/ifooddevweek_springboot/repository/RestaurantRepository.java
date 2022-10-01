package io.luaprogrammer.ifooddevweek_springboot.repository;

import io.luaprogrammer.ifooddevweek_springboot.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
