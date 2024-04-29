package com.anagracetech.book.feedback;

import com.anagracetech.book.book.Book;
import com.anagracetech.book.book.BookRepository;
import com.anagracetech.book.exception.OperationNotPermittedException;
import com.anagracetech.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    public Integer saveFeedback(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID :: " +request.bookId()));
        User user = (User) connectedUser.getPrincipal();
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot provide feedback on an archived or non-shareable book");
        }
        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot provide feedback on your own book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }
}
