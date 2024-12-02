package com.bpl.Production.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bpl.Production.entity.RawMaterial;
import com.bpl.request.RawMaterialRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface RawMaterialService {
	public RawMaterial createRawMaterial(RawMaterialRequest rawMaterialRequest);
    public RawMaterial updateRawMaterial(RawMaterialRequest updateRequest, Integer id) throws Exception ;
    void deleteRawMaterial(Integer id);
    RawMaterial getRawMaterialById(Integer id);
    List<RawMaterial> getAllRawMaterials();
    Page<RawMaterial> getAllRawMaterials(Pageable pageable);
    public void generateExcel(HttpServletResponse response) throws Exception; 
    public void generatePdf(HttpServletResponse response) throws Exception ;
}
