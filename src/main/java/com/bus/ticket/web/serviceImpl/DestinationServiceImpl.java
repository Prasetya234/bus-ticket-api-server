package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.model.Destination;
import com.bus.ticket.web.repository.DestinationRepository;
import com.bus.ticket.web.service.DestinationService;
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
public class DestinationServiceImpl implements DestinationService {

    private DestinationRepository destinationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Destination create(DestinationDTO destinationDTO) {
        Destination destination = modelMapper.map(destinationDTO, Destination.class);
        destination.setUsed(0);
        return destinationRepository.save(destination);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Transactional
    @Override
    public Destination update(Integer id, DestinationDTO destinationDTO) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found " + id));
        destination.setName(destinationDTO.getName());
        return destinationRepository.save(destination);
    }

    @Transactional
    @Override
    public Map<String, Boolean> delete(Integer id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found " + id));
        destinationRepository.delete(destination);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
