package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.response.ModelDelete;
import com.bus.ticket.web.dto.RetingDto;
import com.bus.ticket.web.model.Reting;
import com.bus.ticket.web.repository.RetingRepository;
import com.bus.ticket.web.service.RetingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetingServiceImpl implements RetingService {

    private RetingRepository retingRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RetingServiceImpl(RetingRepository retingRepository, ModelMapper modelMapper) {
        this.retingRepository = retingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Reting create(RetingDto retingDto) {
        return retingRepository.save(modelMapper.map(retingDto, Reting.class));
    }

    @Override
    public List<Reting> findAll() {
        return retingRepository.findAll();
    }

    @Override
    public Reting update(int id, RetingDto retingDto) {
        Reting reting = retingRepository.findById(id).orElseThrow(() -> new NotFoundException("Reting ID Tidak Di Temukan"));
        reting.setName(retingDto.getName());
        reting.setColor(retingDto.getColor());
        return retingRepository.save(reting);
    }

    @Override
    public ModelDelete delete(int id) {
        try {
            retingRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Reting ID Tidak Di Temukan");
        }
        ModelDelete delete = new ModelDelete();
        return delete;
    }
}
