package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.FacilityDTO;
import com.bus.ticket.web.model.Facility;

import java.util.List;
import java.util.Map;

public interface FacilityService {
    Facility addNewFacility(FacilityDTO facility);

    List<Facility> getAllFacility();

    Facility getById(Integer id);

    Facility updateData(Integer id, FacilityDTO facility);

    Map<String, Boolean> deleteFacility(Integer id);
}
