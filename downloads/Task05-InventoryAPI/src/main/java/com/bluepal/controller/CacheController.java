//package com.bluepal.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bluepal.entity.Spares;
//import com.bluepal.service.CachePublisher;
//import com.bluepal.service.SparesService;
//
//@RestController
//@RequestMapping("/cache")
//public class CacheController {
//
//    @Autowired
//    private CachePublisher cachePublisher;
//
//    @PostMapping("/{materialNo}")
//    public ResponseEntity<Void> syncCache(@PathVariable Long materialNo) {
//        Optional<Spares> spare = SparesService.getSparesByMaterialNo(materialNo);
//        if (spare.isPresent()) {
//            // Publish a sync message for the specific spare
//            cachePublisher.publishCacheUpdate("CACHE_UPDATE", "sparesCache", String.valueOf(materialNo));
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//}
