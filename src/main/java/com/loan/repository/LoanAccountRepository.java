package com.loan.repository;

import java.util.List;

import com.loan.entity.LoanAccount;

public interface LoanAccountRepository {
	
	List<LoanAccount> getLoanAccounts();
	LoanAccount saveLoanAccount(LoanAccount account);
	LoanAccount getLoanAccountById(Integer id);
	LoanAccount updateLoanAccount(LoanAccount loanAccount, Integer loanId);


}
