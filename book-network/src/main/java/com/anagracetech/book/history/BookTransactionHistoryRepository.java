package com.anagracetech.book.history;

import com.anagracetech.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query("""
        SELECT 
        (COUNT(*) > 0) AS isBorrowed
        FROM BookTransactionHistory bookTransactionHistory
        WHERE bookTransactionHistory.user.id = :userId
        AND bookTransactionHistory.book.id = :bookId
        AND bookTransactionHistory.returnApproved = false
        """)
    boolean isAlreadyBorrowedByUser(Integer bookId, Integer userId);

    @Query("""
        SELECT transaction
        FROM BookTransactionHistory transaction
        WHERE transaction.user.id = :userId
        AND transaction.book.id = :bookId
        AND transaction.returned = false
        AND transaction.returnApproved = false
        """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId, Integer id);
}
