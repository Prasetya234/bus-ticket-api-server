package com.bus.ticket.web.repository;


import com.bus.ticket.web.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, String> {
}
