package org.example.gameconnectbackend.controllers;


import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.example.gameconnectbackend.services.AdminService;
import org.example.gameconnectbackend.services.UserService;
import org.example.gameconnectbackend.dtos.userDTOs.AdminUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/see-users/{page}/{usersPerPage}")
    public ResponseEntity<List<AdminUserDto>> getPageOfUsers(@PathVariable int page, @PathVariable int usersPerPage) {
        var response = adminService.getPageOfUsers(page, usersPerPage);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete-user")
    public ResponseEntity<Boolean> deleteUser(@RequestParam long id) {
        System.out.println("hello from delete user");
       var response = adminService.deleteUser(id);
       return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<AdminUserDto> updateUser(@PathVariable long id, @RequestBody AdminUserDto adminUserDto) {
        AdminUserDto updatedDto = adminService.adminUpdateUser(adminUserDto);
        return ResponseEntity.ok(adminUserDto);
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<AdminUserDto> getUser(@PathVariable long id) {
        AdminUserDto userDto = adminService.getSingleUser(id);
        return ResponseEntity.ok(userDto);
    }

}
