package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.web.model.HistoryJoinAdmin;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.HistoryJoinAdminRepository;
import com.bus.ticket.web.service.HistoryJoinAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class HistoryJoinAdminSerivceImpl implements HistoryJoinAdminService {

    private HistoryJoinAdminRepository historyJoinAdminRepository;

    @Autowired
    public HistoryJoinAdminSerivceImpl(HistoryJoinAdminRepository historyJoinAdminRepository) {
        this.historyJoinAdminRepository = historyJoinAdminRepository;
    }

    @Transactional
    @Override
    public HistoryJoinAdmin join(User user, String description) {
        if (historyJoinAdminRepository.findByUserId(user).isPresent()) throw new BussinesException("Can't join. You have registered");
        HistoryJoinAdmin historyJoinAdmin = new HistoryJoinAdmin();
        historyJoinAdmin.setUserId(user);
        historyJoinAdmin.setDescription(description);
        return historyJoinAdminRepository.save(historyJoinAdmin);
    }
}
