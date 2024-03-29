package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.DepartureDTO;
import com.bus.ticket.web.model.Departure;
import com.bus.ticket.web.repository.*;
import com.bus.ticket.web.service.DepartureService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DepartureServiceImpl implements DepartureService {

    private ModelMapper modelMapper;
    private DestinationRepository destinationRepository;
    private CompanyRepository companyRepository;
    private DepartureStatusRepository departureStatusRepository;
    private PromoRepository promoRepository;
    private DepartureRepository departureRepository;
    private CompanyEmployeRepository companyEmployeRepository;

    @Autowired
    public DepartureServiceImpl(ModelMapper modelMapper, DestinationRepository destinationRepository, CompanyRepository companyRepository,  DepartureStatusRepository departureStatusRepository, PromoRepository promoRepository,DepartureRepository departureRepository, CompanyEmployeRepository companyEmployeRepository) {
        this.modelMapper = modelMapper;
        this.destinationRepository = destinationRepository;
        this.companyRepository = companyRepository;
        this.promoRepository = promoRepository;
        this.departureStatusRepository = departureStatusRepository;
        this.departureRepository= departureRepository;
        this.companyEmployeRepository = companyEmployeRepository;
    }

    @Transactional
    @Override
    public Departure create(String companyId, String promoId,DepartureDTO departureDTO) {
        Departure create = modelMapper.map(departureDTO, Departure.class);
        create.setPassengerTotal(0);
        if (promoId != null) create.setPromoId(promoRepository.findById(promoId).orElseThrow(() -> new NotFoundException("Promo ID tidak di temukan")));
        create.setCompanyId(companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company ID tidak di temukan")));
        create.setDepartureStatusId(departureStatusRepository.findById(1).get());
        create.setStartDestination(destinationRepository.findById(departureDTO.getStartDestination()).orElseThrow(() -> new NotFoundException("Start Destination ID Not Found")));
        create.setEndDestination(destinationRepository.findById(departureDTO.getStartDestination()).orElseThrow(() -> new NotFoundException("End Destination ID Not Found")));
        create.setFacilityList(String.join(",", departureDTO.getListFacility()));
        create.setPaymentTypeList(String.join(",", departureDTO.getListPaymentType()));
        return departureRepository.save(create);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Departure> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departureRepository.findAll(pageable);
    }

    private Departure getData(String id) {
        return departureRepository.findById(id).get();
    }

    @Override
    public Departure findById(String id) {
        return null;
    }
}
