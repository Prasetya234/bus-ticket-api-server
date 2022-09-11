package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.DepartureDTO;
import com.bus.ticket.web.model.Departure;
import com.bus.ticket.web.repository.CompanyEmployeRepository;
import com.bus.ticket.web.repository.CompanyRepository;
import com.bus.ticket.web.repository.DestinationRepository;
import com.bus.ticket.web.repository.PromoRepository;
import com.bus.ticket.web.service.DepartureService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DepartureServiceImpl implements DepartureService {

    private ModelMapper modelMapper;
    private DestinationRepository destinationRepository;
    private CompanyRepository companyRepository;
    private PromoRepository promoRepository;
    private CompanyEmployeRepository companyEmployeRepository;

    @Autowired
    public DepartureServiceImpl(ModelMapper modelMapper, DestinationRepository destinationRepository, CompanyRepository companyRepository, PromoRepository promoRepository, CompanyEmployeRepository companyEmployeRepository) {
        this.modelMapper = modelMapper;
        this.destinationRepository = destinationRepository;
        this.companyRepository = companyRepository;
        this.promoRepository = promoRepository;
        this.companyEmployeRepository = companyEmployeRepository;
    }

    @Transactional
    @Override
    public Departure create(DepartureDTO departureDTO) {
        Departure create = modelMapper.map(departureDTO, Departure.class);
        create.setPassengerTotal(0);
        if (departureDTO.getPromoId().isEmpty()) create.setPromoId(promoRepository.findById(departureDTO.getPromoId()).orElseThrow(() -> new NotFoundException("Promo ID tidak di temukan")));
        create.setCompanyId(companyRepository.findById(departureDTO.getCompanyId()).orElseThrow(() -> new NotFoundException("Company ID tidak di temukan")));
        return null;
    }

    @Override
    public Page<Departure> findAll(int page, int size) {
        return null;
    }

    @Override
    public Departure findById(String id) {
        return null;
    }
}
