package com.bpl.Production.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bpl.Production.entity.RawMaterialRequirement;
import com.bpl.request.RawMaterialRequirementRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface RawMaterialRequirementService {
	
    RawMaterialRequirement createRawMaterialRequirement(RawMaterialRequirementRequest request);

    RawMaterialRequirement updateRawMaterialRequirement(RawMaterialRequirementRequest request, Integer id) throws Exception;

    void deleteRawMaterialRequirement(Integer id);

    RawMaterialRequirement getRawMaterialRequirementById(Integer id);

    List<RawMaterialRequirement> getAllRawMaterialRequirements();

    Page<RawMaterialRequirement> getAllRawMaterialRequirements(Pageable pageable);
    
    public void generateExcel(HttpServletResponse response) throws Exception; 
    public void generatePdf(HttpServletResponse response) throws Exception ;
}
