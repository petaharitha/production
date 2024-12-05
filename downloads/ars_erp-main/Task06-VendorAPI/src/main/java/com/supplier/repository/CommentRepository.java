package com.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.Comment;
import com.supplier.entity.PurchasingPO;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // This method should exist if your Comment entity has a relationship with PurchasingQuote
    List<Comment> findByQuoteId(Long quoteId);

    // This method should exist if your Comment entity has a relationship with PurchasingRFQ
    List<Comment> findByRfqId(Long rfqId);

//	List<Comment> findByPo(PurchasingPO purchasingPO);
    
    List<Comment> findByPurchasingPO(PurchasingPO purchasingPO);
}



