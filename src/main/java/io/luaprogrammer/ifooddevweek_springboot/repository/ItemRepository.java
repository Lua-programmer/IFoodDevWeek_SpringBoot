package io.luaprogrammer.ifooddevweek_springboot.repository;

import io.luaprogrammer.ifooddevweek_springboot.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
