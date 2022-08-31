package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.UserRoleDTO;
import com.bus.ticket.web.model.UserRole;
import com.bus.ticket.web.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/api/user-role")
public class UserRoleController {

    private UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CommonResponse<UserRole> addNewUserRole(@RequestBody UserRoleDTO userRole){
        return ResponseHelper.successResponse(userRoleService.addNewRole(userRole));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public CommonResponse<List<UserRole>> getAllUserRole(){
        return ResponseHelper.successResponse(userRoleService.getAllData());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CommonResponse<UserRole> updateData(@PathVariable("id") Integer id, @RequestBody UserRoleDTO userRole){
        return ResponseHelper.successResponse(userRoleService.updateData(id, userRole));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    private CommonResponse<Map<String, Boolean>> deleteUserRole(@PathVariable("id") Integer Id){
        return ResponseHelper.successResponse(userRoleService.deleteUserRole(Id));
    }
}
