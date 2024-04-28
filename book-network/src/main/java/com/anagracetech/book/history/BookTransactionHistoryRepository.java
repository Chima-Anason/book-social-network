package com.anagracetech.book.history;

import com.anagracetech.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTransactionHistoryRepository extends JpaRepository <BookTransactionHistory, Integer> {
    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.user.id = :userId
        """)
    Page<BookTransactionHistory> findAllByBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.book.owner.id = :userId
        """)
    Page<BookTransactionHistory> findAllByReturnedBooks(Pageable pageable, Integer id);
}
