package com.boylegu.springboot_vue.controller;

import com.boylegu.springboot_vue.controller.dto.request.LoginRequestDto;
import com.boylegu.springboot_vue.controller.dto.request.PasswordCreateDto;
import com.boylegu.springboot_vue.controller.dto.request.UserCreateDto;
import com.boylegu.springboot_vue.controller.dto.response.UserListResponseDto;
import com.boylegu.springboot_vue.dto.UserDto;

import com.boylegu.springboot_vue.service.user.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserListResponseDto> getUsers()
            throws Exception {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> findLocalUserById(@NotNull @PathVariable("id") UUID id) throws Exception {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "", consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity <UserDto> createUser(@Validated @RequestBody UserCreateDto user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping(value = "/{id}/password", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createPassword(@NotNull @PathVariable("id") UUID id,
                                         @Validated @RequestBody PasswordCreateDto password) throws Exception{
        userService.createPassword(id, password.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto loginRequestDto) throws Exception{
        userService.login(loginRequestDto);
        return ResponseEntity.ok("Logged in Succesfully");
    }
//
//    @DeleteMapping(value = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<List<DeleteUserResponseDto>> deleteLocalUserList(@NotNull @RequestBody DeleteUserRequestDto deleteUserDto)
//            throws NoLocalUserFoundException{
//        return ResponseEntity.ok(localUserService.deleteLocalUserList(deleteUserDto.getUsers()));
//    }
 }