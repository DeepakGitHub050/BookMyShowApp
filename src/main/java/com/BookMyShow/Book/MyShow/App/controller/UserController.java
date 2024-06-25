package com.BookMyShow.Book.MyShow.App.controller;

import com.BookMyShow.Book.MyShow.App.dtos.SignUpUserRequestDto;
import com.BookMyShow.Book.MyShow.App.dtos.SignUpUserResponseDto;
import com.BookMyShow.Book.MyShow.App.models.Enums.ResponseStatus;
import com.BookMyShow.Book.MyShow.App.models.User;
import com.BookMyShow.Book.MyShow.App.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public SignUpUserResponseDto signUp(SignUpUserRequestDto request){
        User user = userService.signUp(request);
        return new SignUpUserResponseDto(user.getId(), ResponseStatus.SUCCESS);
    }

    public boolean login(String email, String password){
        return userService.login(email,password);
    }
}
