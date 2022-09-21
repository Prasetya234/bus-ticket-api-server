package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.DepartureDTO;
import com.bus.ticket.web.model.Departure;
import org.springframework.data.domain.Page;

public interface DepartureService {
    Departure create(String companyId, String promoId, DepartureDTO departureDTO);
    Page<Departure> findAll(int page, int size);
    Departure findById(String id);
}
