package com.anagracetech.book.feedback;

import com.anagracetech.book.book.Book;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(
                        Book.builder()
                                .id(request.bookId())
                                .archived(false) // Not required and has no impact :: Just to satisfy lombok
                                .shareable(false) // Not required and has no impact :: Just to satisfy lombok
                                .build()
                )
                .build();
    }
}
