package com.loan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.loan.common.exception.ValidationException;
import com.loan.common.json.ConvertObjectToJson;
import com.loan.common.json.DateUtils;
import com.loan.controller.LoanAccountControllerImple;
import com.loan.email.dao.EmailEnitity;
import com.loan.email.proxy.EmailSystemProxyApi;
import com.loan.entity.LoanAccount;
import com.loan.origination.dao.Employee;
import com.loan.origination.dao.Organization;
import com.loan.origination.proxy.OriginationProxyApi;
import com.loan.repository.LoanAccountRepository;

@Service
public class LoanAccountServiceImpl implements LoanAccountService {
	private Logger logger = LoggerFactory.getLogger(LoanAccountServiceImpl.class);
	@Autowired
	private LoanAccountRepository loanAccountRepository;

	@Autowired
	private OriginationProxyApi originationProxyApi;

	@Autowired
	private EmailSystemProxyApi emailSystemProxyApi;

	@Override
	public List<LoanAccount> getLoanAccounts() {
		return loanAccountRepository.getLoanAccounts();
	}

	@Override
	public LoanAccount saveLoanAccount(LoanAccount account) {
		logger.info("Started LoanAccountServiceImpl.saveLoanAccount() method :{},{}",
				ConvertObjectToJson.convertObjectToJsonString(account), DateUtils.getDateToYYYMMDD(new Date()));
		Organization organization = null;

		account.setCreateTimeStamp(new Date());
		account.setModificationTimeStamp(new Date());
		logger.info("Calling originationProxyApi.getOrganizationDetails() method :{}",
				DateUtils.getDateToYYYMMDD(new Date()));
		List<Organization> organizationDetails = originationProxyApi.getOrganizationDetails();

		logger.info("Response Object from originationProxyApi.getOrganizationDetails() method :{},{}",
				ConvertObjectToJson.convertObjectToJsonString(organizationDetails),
				DateUtils.getDateToYYYMMDD(new Date()));
		Employee employee = originationProxyApi.getEmployeeByIdWithOrgName(account.getEmpId(),
				account.getCompanyName());
		System.out.println(employee);
		if (!CollectionUtils.isEmpty(organizationDetails)) {
			organization = organizationDetails.stream()
					.filter(org -> org.getOrigName().equalsIgnoreCase(account.getCompanyName())).findAny().get();
			System.out.println(organization);
		}
		LoanAccount saveLoanAccount = loanAccountRepository.saveLoanAccount(account);
		if (saveLoanAccount.getId() != null) {
			logger.info("triggerEmail :{},{}", DateUtils.getDateToYYYMMDD(new Date()));
			this.triggerEmail(organization, saveLoanAccount, employee, "Created");
		}

		logger.info("End LoanAccountServiceImpl.saveLoanAccount() method :{},{}",
				ConvertObjectToJson.convertObjectToJsonString(saveLoanAccount), DateUtils.getDateToYYYMMDD(new Date()));
		return saveLoanAccount;
	}

	private void triggerEmail(Organization organization, LoanAccount saveLoanAccount, Employee employee,
			String created) {
		EmailEnitity emailEnitity = new EmailEnitity();
		emailEnitity.setId(saveLoanAccount.getEmpId());
		emailEnitity.setOrgId(organization.getId());
		emailEnitity.setLoanTerms(saveLoanAccount.getLoanTerms());
		emailEnitity.setEmpName(employee.getEmpName());
		emailEnitity.setMonthlyAmount(saveLoanAccount.getMonthlyAmount());
		emailEnitity.setOrignalLoanAmount(saveLoanAccount.getOrignalLoanAmount());
		emailEnitity.setOutStandingAmount(saveLoanAccount.getOutStandingAmount());
		emailEnitity.setCreatedTimeStamp(saveLoanAccount.getCreateTimeStamp());
		emailEnitity.setModifiedTimeStamp(saveLoanAccount.getModificationTimeStamp());
		emailEnitity.setPhoneNumber(employee.getPhoneNumber());
		emailEnitity.setEmilSubject(created);
		emailEnitity.setLoanNumber(String.valueOf(saveLoanAccount.getId()));
		emailEnitity.setTotalPaidAmount(
				saveLoanAccount.getTotalPaidAmount() != null ? saveLoanAccount.getTotalPaidAmount() : "");
		try {

			emailSystemProxyApi.saveEmployee(emailEnitity);
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Email system error message :" + e.getCause().getLocalizedMessage());
		}
	}

	@Override
	public LoanAccount getLoanAccountById(Integer id) {
		LoanAccount loanAccount = loanAccountRepository.getLoanAccountById(id);

		if (null == loanAccount) {
			throw new ValidationException("Loan account not exist in loan system ", "404");
		}
		return loanAccount;
	}

	@Override
	public LoanAccount updateLoanAccount(LoanAccount loanAccount, Integer loanId) {
		Organization organization = null;
		LoanAccount account = loanAccountRepository.getLoanAccountById(loanId);

		// Checking account object is empty or not. If not doing calcu
		if (Optional.ofNullable(account).isPresent()) {

			if (!account.getTotalPaidAmount().isEmpty()) {
				int monthMount = Integer.parseInt(account.getMonthlyAmount());
				int totalPaid = Integer.parseInt(account.getTotalPaidAmount());
				int outstanding = Integer.parseInt(account.getOutStandingAmount());
				int totalPaid1 = totalPaid + monthMount;

				Integer orignalLoanAmount = account.getOrignalLoanAmount();
				if (orignalLoanAmount >= totalPaid) {
					Integer outstanding1 = orignalLoanAmount - totalPaid1;
					account.setTotalPaidAmount(String.valueOf(totalPaid1));
					account.setOutStandingAmount(String.valueOf(outstanding1));
				}
			}

		} else {
			throw new ValidationException("404", "Loan account not exist in loan system ");
		}

		LoanAccount updateLoanAccount = loanAccountRepository.updateLoanAccount(account, loanId);
		List<Organization> organizationDetails = originationProxyApi.getOrganizationDetails();
		organization = organizationDetails.stream()
				.filter(org -> org.getOrigName().equalsIgnoreCase(account.getCompanyName())).findAny().get();
		Employee employee = originationProxyApi.getEmployeeByIdWithOrgName(account.getEmpId(),
				account.getCompanyName());

		this.triggerEmail(organization, updateLoanAccount, employee, "Loan Updated");
		return updateLoanAccount;
	}

}
