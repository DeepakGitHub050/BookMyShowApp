package com.BookMyShow.Book.MyShow.App.dtos;

import com.BookMyShow.Book.MyShow.App.models.Enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookShowResponseDto {
    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
    private String failueReason;
}
