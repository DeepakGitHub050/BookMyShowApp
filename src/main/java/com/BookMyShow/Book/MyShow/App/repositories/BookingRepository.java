package com.BookMyShow.Book.MyShow.App.repositories;

import com.BookMyShow.Book.MyShow.App.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
