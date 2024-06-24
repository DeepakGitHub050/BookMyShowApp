package com.BookMyShow.Book.MyShow.App.repositories;

import com.BookMyShow.Book.MyShow.App.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {
}
