package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.DepartureStatusDTO;
import com.bus.ticket.web.model.DepartureStatus;
import com.bus.ticket.web.repository.DepartureStatusRepository;
import com.bus.ticket.web.service.DepartureStatusService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DepartureStatusServiceImpl implements DepartureStatusService {

    private DepartureStatusRepository departureStatusRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DepartureStatusServiceImpl(DepartureStatusRepository departureStatusRepository, ModelMapper modelMapper) {
        this.departureStatusRepository = departureStatusRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public DepartureStatus create(DepartureStatusDTO DepartureStatusDTO) {
        return departureStatusRepository.save(modelMapper.map(DepartureStatusDTO, DepartureStatus.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepartureStatus> findAll() {
        return departureStatusRepository.findAll();
    }

    @Transactional
    @Override
    public DepartureStatus update(Integer id, DepartureStatusDTO departureStatusDTO) {
        DepartureStatus departureStatus = departureStatusRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found " + id));
        departureStatus.setName(departureStatusDTO.getName());
        return departureStatusRepository.save(departureStatus);
    }

    @Transactional
    @Override
    public Map<String, Boolean> delete(Integer id) {
        DepartureStatus departureStatus = departureStatusRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found " + id));
        departureStatusRepository.delete(departureStatus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
