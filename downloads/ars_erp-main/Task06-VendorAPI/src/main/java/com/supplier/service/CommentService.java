package com.supplier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.entity.Comment;
import com.supplier.entity.PurchasingQuote;
import com.supplier.entity.PurchasingRFQ;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.CommentRepository;
import com.supplier.repository.PurchasingQuoteRepository;
import com.supplier.repository.PurchasingRFQRepository;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PurchasingQuoteRepository purchasingQuoteRepository;

    @Autowired
    private PurchasingRFQRepository purchasingRFQRepository;

    public Comment addCommentToQuote(Long quoteId, Comment comment) {
        logger.info("Adding comment to quote with ID: {}", quoteId);
        
        // Fetch the PurchasingQuote using the provided quoteId
        PurchasingQuote quote = purchasingQuoteRepository.findById(quoteId)
                .orElseThrow(() -> {
                    logger.error("Quote not found with id: {}", quoteId);
                    return new ResourceNotFoundException("Quote not found with id " + quoteId);
                });

        // Set the quote in the comment
        comment.setQuote(quote);

        logger.info("Comment before saving: {}", comment); // Check the state of the object

        // Save the comment
        Comment savedComment = commentRepository.save(comment);
        
        logger.info("Comment added to quote with ID: {}", quoteId);
        return savedComment;
    }


    public Comment addCommentToRfq(Long rfqId, Comment comment) {
        logger.info("Adding comment to RFQ with ID: {}", rfqId);
        // Fetch the PurchasingRFQ using the provided rfqId
        PurchasingRFQ rfq = purchasingRFQRepository.findById(rfqId)
                .orElseThrow(() -> {
                    logger.error("RFQ not found with id: {}", rfqId);
                    return new ResourceNotFoundException("RFQ not found with id " + rfqId);
                });

        // Set the RFQ in the comment
        comment.setRfq(rfq);
        Comment savedComment = commentRepository.save(comment);
        logger.info("Comment added to RFQ with ID: {}", rfqId);
        return savedComment;
    }

    public List<Comment> getCommentsForQuote(Long quoteId) {
        logger.info("Fetching comments for quote with ID: {}", quoteId);
        // Fetch comments related to the specified quote ID
        List<Comment> comments = commentRepository.findByQuoteId(quoteId);
        logger.info("Retrieved {} comments for quote with ID: {}", comments.size(), quoteId);
        return comments;
    }

    public List<Comment> getCommentsForRfq(Long rfqId) {
        logger.info("Fetching comments for RFQ with ID: {}", rfqId);
        // Fetch comments related to the specified RFQ ID
        List<Comment> comments = commentRepository.findByRfqId(rfqId);
        logger.info("Retrieved {} comments for RFQ with ID: {}", comments.size(), rfqId);
        return comments;
    }

    public void deleteComment(Long commentId) {
        logger.info("Deleting comment with ID: {}", commentId);
        // Fetch the comment by ID and delete it
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    logger.error("Comment not found with id: {}", commentId);
                    return new ResourceNotFoundException("Comment not found with id " + commentId);
                });
        commentRepository.delete(comment);
        logger.info("Comment with ID: {} deleted successfully", commentId);
    }
}
