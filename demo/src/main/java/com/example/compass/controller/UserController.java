///*
// * Copyright (c) Icanio
// */
//
//package com.example.compass.controller;
//
//import com.example.compass.dto.GenericResponse;
//import com.example.compass.dto.UserDTO;
//import com.example.compass.services.UserServices;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api/v1/users")
//public class UserController {
//
//
//    private final UserServices userServices;
//
//    public UserController(UserServices userServices) {
//        this.userServices = userServices;
//    }
//
//    @PostMapping
//    public GenericResponse createUsers(@RequestBody UserDTO userDTO) {
//         return new GenericResponse(userServices.createUsers(userDTO));
//    }
//
//    @PutMapping("/{id}")
//    public GenericResponse updateUsers(@PathVariable String id,@RequestBody UserDTO userDTO) {
//        return new GenericResponse(userServices.updateUsers(id,userDTO));
//    }
//
//    @GetMapping("/{id}")
//    public GenericResponse getByUserId(@PathVariable String id) {
//        return new GenericResponse(userServices.getByUserId(id));
//    }
//
////    @GetMapping
////    public GenericResponse getByAllUser(@RequestParam int page,@RequestParam int size) {
////        return new GenericResponse(userServices.getByAllUser(page,size));
////    }
//
//    @GetMapping
//    public GenericResponse getByAllUser() {
//        return new GenericResponse(userServices.getByAllUsers());
//    }
//
//    @DeleteMapping("/{id}")
//    public GenericResponse deleteUser(@PathVariable String id) {
//        return new GenericResponse(userServices.deleteUser(id));
//    }
//
//
//
//}
