package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.DepartureStatusDTO;
import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.model.DepartureStatus;
import com.bus.ticket.web.model.Destination;

import java.util.List;
import java.util.Map;

public interface DepartureStatusService {
    DepartureStatus create(DepartureStatusDTO destinationDTO);
    List<DepartureStatus> findAll();
    DepartureStatus update(Integer id, DepartureStatusDTO destinationDTO);
    Map<String, Boolean> delete(Integer id);
}
