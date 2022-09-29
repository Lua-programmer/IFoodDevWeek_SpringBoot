package io.luaprogrammer.ifooddevweek_springboot.repository;

import io.luaprogrammer.ifooddevweek_springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
