package com.BookMyShow.Book.MyShow.App.services;

import com.BookMyShow.Book.MyShow.App.Exception.*;
import com.BookMyShow.Book.MyShow.App.dtos.BookShowRequestDto;
import com.BookMyShow.Book.MyShow.App.models.Booking;
import com.BookMyShow.Book.MyShow.App.models.Enums.*;
import com.BookMyShow.Book.MyShow.App.models.*;
import com.BookMyShow.Book.MyShow.App.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    public Booking bookShow(BookShowRequestDto request) throws UserIsNotValid, ShowNotFound, SeatNotAvailable{
        Optional<User> user = userRepository.findById(request.getUserId());

        if(!user.isPresent())
            throw new UserIsNotValid();

        Optional<Show> show = showRepository.findById(request.getShowId());
        if(!show.isPresent())
            throw new ShowNotFound();

        List<ShowSeat> reserveSeats = reserveShowSeats(request.getShowId());

        return reserveBooking(request, user, reserveSeats);
    }

    private Booking reserveBooking(BookShowRequestDto request, Optional<User> user, List<ShowSeat> showSeats){
        Booking booking = new Booking();
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculator(request.getShowSeatIds(),request.getShowId()));
        booking.setUser(user.get());
        booking.setShowSeats(showSeats);
        booking.setPayments(new ArrayList<>());

        return bookingRepository.save(booking);
    }

    private int priceCalculator(List<Long> showSeatIds, Long showId){
        return 0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveShowSeats(List<Long> showSeatIds) throws SeatNotAvailable{
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat: showSeats){
            seatNotAvailableForBooking(showSeat);
        }

        List<ShowSeat> reservedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat: showSeats){
            showSeat.setStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            reservedShowSeats.add(showSeatRepository.save(showSeat));
        }
        return reservedShowSeats;
    }

    private static boolean seatNotAvailableForBooking(ShowSeat showSeat) throws SeatNotAvailable{
        if(!ShowSeatStatus.AVAILABLE.equals(showSeat.getStatus())){
            if(ShowSeatStatus.BOOKED.equals(showSeat.getStatus()))
                throw new SeatNotAvailable();
            if(ShowSeatStatus.LOCKED.equals(showSeat.getStatus())){
                Long lockedDuration = Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes();
                if(lockedDuration<10)
                    throw new SeatNotAvailable();
            }
        }
        return true;
    }

}
