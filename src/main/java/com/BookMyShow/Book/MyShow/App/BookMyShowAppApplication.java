package com.BookMyShow.Book.MyShow.App;

import com.BookMyShow.Book.MyShow.App.controller.UserController;
import com.BookMyShow.Book.MyShow.App.dtos.SignUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowAppApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowAppApplication.class,args);
	}

	@Override
	public void run(String... args) throws Exception{
		SignUpUserRequestDto request = new SignUpUserRequestDto();
		request.setEmail("dks1234@fmail.com");
		request.setName("DKS");
		request.setPassword("deepak12345");
		userController.signUp(request);

		userController.login("dks1234@fmail.com","deepak12345");
	}

}
