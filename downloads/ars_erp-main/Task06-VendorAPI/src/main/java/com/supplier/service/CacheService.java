package com.supplier.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingRFQ;
import com.supplier.entity.Vendor;

@Service
public class CacheService {
	 private final Map<Long, Vendor> vendorCache = new ConcurrentHashMap<>();
	    private final Map<Long, PurchasingRFQ> purchasingRFQCache = new ConcurrentHashMap<>();

    public void putVendor(Long vendorId, Vendor vendor) {
        vendorCache.put(vendorId, vendor);
        System.out.println("Added vendor to cache: " + vendor);
    }

    public void putRFQ(Long purchasingRFQId, PurchasingRFQ purchasingRFQ) {
        purchasingRFQCache.put(purchasingRFQId, purchasingRFQ);
        System.out.println("Added PurchasingRFQ to cache: " + purchasingRFQ);
    }

    public void invalidateVendorCache(Long vendorId) {
        vendorCache.remove(vendorId);
        System.out.println("Invalidated vendor cache for ID: " + vendorId);
    }

    public void invalidateRFQCache(Long purchasingRFQId) {
        purchasingRFQCache.remove(purchasingRFQId);
        System.out.println("Invalidated PurchasingRFQ cache for ID: " + purchasingRFQId);
    }
}
