package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.web.dto.FacilityDTO;
import com.bus.ticket.web.model.Facility;
import com.bus.ticket.web.repository.FacilityRepository;
import com.bus.ticket.web.service.FacilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacilityServiceImpl implements FacilityService {

    private ModelMapper modelMapper;
    private FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(ModelMapper modelMapper, FacilityRepository facilityRepository) {
        this.modelMapper = modelMapper;
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility addNewFacility(FacilityDTO facility) {
        return facilityRepository.save(modelMapper.map(facility, Facility.class));
    }

    @Override
    public List<Facility> getAllFacility() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility getById(Integer id) {
        return facilityRepository.findById(id).get();
    }

    @Override
    public Facility updateData(Integer id, FacilityDTO facility) {
        Facility updateFacility = facilityRepository.findById(id).get();
        updateFacility.setName(facility.getName());
        return facilityRepository.save(updateFacility);
    }

    @Override
    public Map<String, Boolean> deleteFacility(Integer id) {
        Facility updateFacility = facilityRepository.findById(id).get();
        facilityRepository.delete(updateFacility);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
