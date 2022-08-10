package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.PaymentTypeDTO;
import com.bus.ticket.web.model.Facility;
import com.bus.ticket.web.model.PaymentType;
import com.bus.ticket.web.repository.PaymentTypeRepository;
import com.bus.ticket.web.service.PaymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private ModelMapper modelMapper;
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentTypeServiceImpl(ModelMapper modelMapper, PaymentTypeRepository paymentTypeRepository) {
        this.modelMapper = modelMapper;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public PaymentType addNew(PaymentTypeDTO paymentType) {
        return paymentTypeRepository.save(modelMapper.map(paymentType, PaymentType.class));
    }

    @Override
    public List<PaymentType> getAllPayment() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public PaymentType updatePaymentType(Integer id, PaymentTypeDTO paymentTypeDTO) {
        PaymentType paymentType = paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Reting ID Tidak Di Temukan"));
        paymentType.setName(paymentTypeDTO.getName());
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public Map<String, Boolean> delete(Integer id) {
        PaymentType paymentType = paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Reting ID Tidak Di Temukan"));
        paymentTypeRepository.delete(paymentType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
