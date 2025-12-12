package org.example.gameconnectbackend.controllers;


import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.example.gameconnectbackend.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @CrossOrigin
    @GetMapping("see-users/{page}/{usersPerPage}")
    public ResponseEntity<List<AdminUserDto>> getPageOfUsers(@PathVariable int page, @PathVariable int usersPerPage) {
        var response = adminService.getPageOfUsers(page, usersPerPage);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @DeleteMapping("/delete-user")
    public ResponseEntity<Boolean> deleteUser(@RequestParam long id) {
        System.out.println("hello from delete user");
       var response = adminService.deleteUser(id);
       return ResponseEntity.ok(response);
    }
}
