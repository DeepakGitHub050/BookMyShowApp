package com.BookMyShow.Book.MyShow.App.models;

import com.BookMyShow.Book.MyShow.App.models.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus status;

    @ManyToOne
    private User user;

    @OneToMany
    private List<ShowSeat> showSeats;
    private int amount;

    @OneToMany
    private List<Payment> payments;
}
