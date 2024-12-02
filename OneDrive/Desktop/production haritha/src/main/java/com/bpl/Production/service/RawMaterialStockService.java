package com.bpl.Production.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bpl.Production.entity.RawMaterialStock;
import com.bpl.request.RawMaterialStockRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface RawMaterialStockService {
    RawMaterialStock createRawMaterialStock(RawMaterialStockRequest request);

    RawMaterialStock updateRawMaterialStock(RawMaterialStockRequest request, Integer id) throws Exception;

    void deleteRawMaterialStock(Integer id);

    RawMaterialStock getRawMaterialStockById(Integer id);

    List<RawMaterialStock> getAllRawMaterialStocks();

    Page<RawMaterialStock> getAllRawMaterialStocks(Pageable pageable);
    
    public void generateExcel(HttpServletResponse response) throws Exception; 
    
    public void generatePdf(HttpServletResponse response) throws Exception ;
}
