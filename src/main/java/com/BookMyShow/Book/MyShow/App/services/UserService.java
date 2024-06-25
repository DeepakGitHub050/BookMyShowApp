package com.BookMyShow.Book.MyShow.App.services;

import com.BookMyShow.Book.MyShow.App.dtos.SignUpUserRequestDto;
import com.BookMyShow.Book.MyShow.App.models.User;
import com.BookMyShow.Book.MyShow.App.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(SignUpUserRequestDto request){
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if(user.isPresent())
            throw new RuntimeException();

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        String password = request.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        newUser.setPassword(encoder.encode(password));

        return userRepository.save(newUser);
    }

    public boolean login(String email, String Password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new RuntimeException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(Password, user.get().getPassword());
    }
}
