package com.loan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.LoanAccount;
import com.loan.service.LoanAccountService;

@RequestMapping("/api/loanAccount")
@RestController
@ResponseBody
public class LoanAccountControllerImple implements LoanAccountController {
	private Logger logger = LoggerFactory.getLogger(LoanAccountControllerImple.class);
	
	@Autowired
	private LoanAccountService accountService;

	@Override
	public List<LoanAccount> getLoanAccounts() {
		return accountService.getLoanAccounts();
	}

	@Override
	public LoanAccount saveLoanAccount(LoanAccount loanAccount) {
		// TODO Auto-generated method stub
		logger.info("### Started LoanAccountControllerImple.saveLoanAccount ###");
		return accountService.saveLoanAccount(loanAccount);
	}

	@Override
	public LoanAccount updateLoanAccount(LoanAccount loanAccount, Integer loanId) {
		return accountService.updateLoanAccount(loanAccount, loanId);
	}

	@Override
	public Response getLoanAccount(Integer loanId) {
		List<LoanAccount> loanAccounts=new ArrayList<LoanAccount>();
		LoanAccount loanAccount = accountService.getLoanAccountById(12);
		if(Optional.ofNullable(loanId).isPresent()) {
			loanAccounts.add(loanAccount);
		}else {
		loanAccounts = accountService.getLoanAccounts();
		}
		
		Response build = Response.ok(loanAccount, MediaType.APPLICATION_JSON).build();
		return build;
	}

}
