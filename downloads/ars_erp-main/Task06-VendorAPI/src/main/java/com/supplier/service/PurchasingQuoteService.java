//package com.supplier.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.supplier.entity.Comment;
//import com.supplier.entity.PurchasingQuote;
//import com.supplier.entity.PurchasingQuoteLineItem;
//import com.supplier.entity.Vendor;
//import com.supplier.exception.ResourceNotFoundException;
//import com.supplier.repository.CommentRepository;
//import com.supplier.repository.PurchasingQuoteRepository;
//import com.supplier.repository.VendorRepository;
//
//@Service
//public class PurchasingQuoteService {
//
//	@Autowired
//	private PurchasingQuoteRepository quoteRepository;
//
//	@Autowired
//	private VendorRepository venndorRepository;
//
//	@Autowired
//	private CommentRepository commentRepository;
//
//	// Create a new Purchasing Quote
//	public PurchasingQuote createQuote(PurchasingQuote quote) {
//		Vendor vendor = quote.getVendor();
//		if (vendor != null && vendor.getId() != null) {
////             Fetch supplier from the database to ensure it's managed
//			vendor = venndorRepository.findById(vendor.getId())
//					.orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
//			quote.setVendor(vendor); // Reattach managed Supplier
//		}
////
//		// Ensure that each line item has the reference to the parent PurchasingQuote
//		for (PurchasingQuoteLineItem item : quote.getLineItems()) {
//			item.setPurchasingQuote(quote);
//		}
//
////         Save the PurchasingQuote, which will cascade save the line items
//		return quoteRepository.save(quote);
//	}
//
//	// Get all Purchasing Quotes
//	public List<PurchasingQuote> getAllQuotes() {
//		return quoteRepository.findAll();
//	}
//
//	// Get a specific Purchasing Quote by ID
//	public PurchasingQuote getQuoteById(Long id) {
//		return quoteRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
//	}
//
//	// Update a Purchasing Quote by ID
//	public PurchasingQuote updateQuote(Long id, PurchasingQuote quoteDetails) {
//		PurchasingQuote quote = quoteRepository.findById(id).orElse(null);
//		if (quote != null) {
//			quote.setAmount(quoteDetails.getAmount());
//			quote.setApprovedBy(quoteDetails.getApprovedBy());
//			quote.setStatus(quoteDetails.getStatus());
//			quote.setDate(quoteDetails.getDate());
//			quote.setNumber(quoteDetails.getNumber());
//			return quoteRepository.save(quote);
//		}
//		return null;
//	}
//
//	// Delete a Purchasing Quote by ID
//	public void deleteQuote(Long id) {
//		PurchasingQuote quote = quoteRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
//		quoteRepository.delete(quote);
//	}
//
//	// Add a new comment to a Purchasing Quote
//	public void addComment(Long quoteId, String commentContent) {
//		PurchasingQuote quote = quoteRepository.findById(quoteId).orElse(null);
//		if (quote != null) {
//			Comment comment = new Comment();
//			comment.setContent(commentContent);
//			comment.setQuote(quote);
//			commentRepository.save(comment);
//		}
//	}
//
//	// Get all comments for a specific Purchasing Quote
//	public List<Comment> getComments(Long quoteId) {
//		return commentRepository.findByQuoteId(quoteId);
//	}
//
//	// Compare two quotes by their IDs
//	public Map<String, Object> compareQuotes(List<Long> quoteIds) {
//		List<PurchasingQuote> quotes = quoteRepository.findAllById(quoteIds);
//		Map<String, Object> comparisonResult = new HashMap<>();
//
//		if (quotes.isEmpty()) {
//			comparisonResult.put("message", "No quotes found for the provided IDs.");
//			return comparisonResult;
//		}
////
//		// Initialize variables for comparison
//		double minAmount = Double.MAX_VALUE;
//		double maxAmount = Double.MIN_VALUE;
//		double totalAmount = 0.0;
//		int quoteCount = quotes.size();
//
//		for (PurchasingQuote quote : quotes) {
//			double amount = quote.getAmount();
//			totalAmount += amount;
//			if (amount < minAmount) {
//				minAmount = amount;
//			}
//			if (amount > maxAmount) {
//				maxAmount = amount;
//			}
//		}
//
//		double averageAmount = totalAmount / quoteCount;
//
//		// Prepare comparison results
//		comparisonResult.put("minAmount", minAmount);
//		comparisonResult.put("maxAmount", maxAmount);
//		comparisonResult.put("averageAmount", averageAmount);
//		comparisonResult.put("totalAmount", totalAmount);
//		comparisonResult.put("quoteCount", quoteCount);
//
//		return comparisonResult;
//	}
//}
