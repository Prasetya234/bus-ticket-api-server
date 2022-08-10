package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.UserRoleDTO;
import com.bus.ticket.web.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleService {
    UserRole addNewRole (UserRoleDTO userRole);

    List<UserRole> getAllData();

    UserRole getById(Integer id);

    UserRole updateData(Integer id, UserRoleDTO userRole);

    Map<String, Boolean> deleteUserRole(Integer id);
}
