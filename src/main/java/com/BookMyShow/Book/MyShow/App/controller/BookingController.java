package com.BookMyShow.Book.MyShow.App.controller;

import com.BookMyShow.Book.MyShow.App.Exception.SeatNotAvailable;
import com.BookMyShow.Book.MyShow.App.Exception.ShowNotFound;
import com.BookMyShow.Book.MyShow.App.Exception.UserIsNotValid;
import com.BookMyShow.Book.MyShow.App.dtos.BookShowRequestDto;
import com.BookMyShow.Book.MyShow.App.dtos.BookShowResponseDto;
import com.BookMyShow.Book.MyShow.App.models.Booking;
import com.BookMyShow.Book.MyShow.App.models.Enums.ResponseStatus;
import com.BookMyShow.Book.MyShow.App.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private BookingService bookingService;

    private static final String USER_INVALID_MSG = "User Id Invalid";
    private static final String SHOW_INVALID_MSG = "Show id Invalid";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookShowResponseDto bookShow(BookShowRequestDto request){
        try {
            Booking booking = bookingService.bookShow(request);
            return new BookShowResponseDto(booking.getId(), booking.getAmount(), ResponseStatus.SUCCESS, "SUCCESS");
        }catch (UserIsNotValid e){
            return new BookShowResponseDto(null,0,ResponseStatus.FAILURE,USER_INVALID_MSG);
        }catch (ShowNotFound e){
            return new BookShowResponseDto(null,0,ResponseStatus.FAILURE,SHOW_INVALID_MSG);
        }catch (SeatNotAvailable e){
            return new BookShowResponseDto(null,0,ResponseStatus.FAILURE,SOMETHING_WENT_WRONG);
        }
    }
}
