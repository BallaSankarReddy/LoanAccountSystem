package com.loan.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.loan.common.BaseRepository;
import com.loan.entity.LoanAccount;

@Repository
public class LoanAccountRepositoryImpl extends BaseRepository<LoanAccount> implements LoanAccountRepository {

	@Override
	public List<LoanAccount> getLoanAccounts() {
		// TODO Auto-generated method stub
		return this.findAll(LoanAccount.class);
	}

	@Override
	public LoanAccount saveLoanAccount(LoanAccount account) {
		// TODO Auto-generated method stub
		int accountId = this.create(account);
		account.setId(accountId);
		return account;
	}

	@Override
	public LoanAccount getLoanAccountById(Integer id) {
		return this.findById(LoanAccount.class, id);
	}

	@Override
	public LoanAccount updateLoanAccount(LoanAccount loanAccount, Integer loanId) {

		Map<String,Object> updatedColoumMap = new HashMap<String,Object>();
		
		updatedColoumMap.put("OutStandingAmount", loanAccount.getOutStandingAmount());
		updatedColoumMap.put("totalPaidAmount",loanAccount.getTotalPaidAmount());
		Map<String,Object> restrictedColoumnsMap = new HashMap<String,Object>();
		
		restrictedColoumnsMap.put("id", loanAccount.getId());
		int id = this.update(loanAccount, updatedColoumMap, restrictedColoumnsMap);
		
		//loanAccount.setId(id);
		return loanAccount;
	}

}
