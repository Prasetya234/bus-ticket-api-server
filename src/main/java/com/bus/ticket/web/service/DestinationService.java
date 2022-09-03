package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.model.Destination;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DestinationService {
    Destination create(DestinationDTO destinationDTO);
    Page<Destination> findAll(int page, int size);
    Destination update(Integer id, DestinationDTO destinationDTO);
    Map<String, Boolean> delete(Integer id);
}
