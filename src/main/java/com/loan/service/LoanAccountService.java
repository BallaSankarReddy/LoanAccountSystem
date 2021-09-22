package com.loan.service;

import java.util.List;

import com.loan.entity.LoanAccount;

public interface LoanAccountService {
	
	List<LoanAccount> getLoanAccounts();
	LoanAccount saveLoanAccount(LoanAccount account);
	LoanAccount getLoanAccountById(Integer id);
	LoanAccount updateLoanAccount(LoanAccount loanAccount, Integer loanId);

}
