package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.UserRoleDTO;
import com.bus.ticket.web.model.UserRole;
import com.bus.ticket.web.repository.UserRoleRepository;
import com.bus.ticket.web.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRole addNewRole(UserRoleDTO userRole) {
        return userRoleRepository.save(modelMapper.map(userRole, UserRole.class));
    }

    @Override
    public List<UserRole> getAllData() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole updateData(Integer id, UserRoleDTO userRoleDto) {
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found" + id));
        userRole.setName(userRoleDto.getName());
        return userRoleRepository.save(userRole);
    }

    @Override
    public Map<String, Boolean> deleteUserRole(Integer id) {
        UserRole updateUserRole = userRoleRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found" + id));
        userRoleRepository.delete(updateUserRole);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
