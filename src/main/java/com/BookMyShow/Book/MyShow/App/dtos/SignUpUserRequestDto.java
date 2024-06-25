package com.BookMyShow.Book.MyShow.App.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDto {
    private String name;
    private String email;
    private String password;
}
