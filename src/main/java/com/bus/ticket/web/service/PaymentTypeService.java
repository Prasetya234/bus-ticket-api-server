package com.bus.ticket.web.service;


import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.PaymentTypeDTO;
import com.bus.ticket.web.model.PaymentType;

import java.util.List;
import java.util.Map;

public interface PaymentTypeService {
    PaymentType addNew(PaymentTypeDTO paymentType);

    List<PaymentType> getAllPayment();

    PaymentType updatePaymentType(Integer id, PaymentTypeDTO paymentTypeDTO);

    Map<String, Boolean> delete(Integer id);
}
