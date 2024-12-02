package com.bpl.Production.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bpl.Production.entity.RawMaterialIssue;
import com.bpl.request.RawMaterialIssueRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface RawMaterialIssueService {
    RawMaterialIssue createRawMaterialIssue(RawMaterialIssueRequest rawMaterialIssueRequest);

    RawMaterialIssue updateRawMaterialIssue(RawMaterialIssueRequest rawMaterialIssueRequest, Integer id) throws Exception;

    void deleteRawMaterialIssue(Integer id);

    RawMaterialIssue getRawMaterialIssueById(Integer id);

    List<RawMaterialIssue> getAllRawMaterialIssues();

    Page<RawMaterialIssue> getAllRawMaterialIssues(Pageable pageable);
    
    public void generateExcel(HttpServletResponse response) throws Exception; 
    public void generatePdf(HttpServletResponse response) throws Exception ;
}
