package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.SiloMaterialTestResult;
import com.bpl.Production.repository.SiloMaterialTestResultRepository;


@Service
public class SiloMaterialTestResultService {

	@Autowired
	private SiloMaterialTestResultRepository repository;

	public SiloMaterialTestResult create(SiloMaterialTestResult result) {
		return repository.save(result);
	}

	public SiloMaterialTestResult update(SiloMaterialTestResult result) {
		if (repository.existsById(result.getId())) {
			return repository.save(result);
		} else {
			throw new RuntimeException("Result not found");
		}
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Optional<SiloMaterialTestResult> findById(Integer id) {
		return repository.findById(id);
	}

	public Page<SiloMaterialTestResult> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<SiloMaterialTestResult> filterByShift(String shift) {
		return repository.findByShift(shift);
	}

//    @Transactional
//    public void approveAndSendEmail(Integer id, Integer userId) throws Exception {
//        // Fetch the SiloMaterialTestResults by ID
//        Optional<SiloMaterialTestResult> siloList = siloMaterialTestResultRepository.findById(id);
//        if (!siloList.isEmpty()) {
//            SiloMaterialTestResult siloMaterial = siloList.get();
//            siloMaterial.setApprovalBy(userId);
//            siloMaterial.setStatus(2);
//            siloMaterialTestResultRepository.save(siloMaterial);
//        }
//
//        // Fetch the User by ID
//        List<User> userList = userRepository.findById(userId);
//        if (!userList.isEmpty()) {
//            User user = userList.get(0);
//            String email = user.getEmail();
//            String userName = user.getName();
//
//            // Prepare email content
//            String subject = ": Approved and ready to be released for Production!";
//            String message = "Order has been approved by " + userName + " and is ready to be released for production. Please login into the system and proceed further with the process.<br/><br/><br/> Thank you";
//
//            // Send the email
//            logisticsHeadMailService.sendMail(email, subject, message, userName);
//        }
}
