package com.loan.controller;

import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.LoanAccount;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "LoanAccountController", description = "REST APIs related to LoanAccount Entity!!!!")
@RequestMapping("/api/loanAccount")
@RestController

public interface LoanAccountController {

	@ApiOperation(value = "Get list of employee in the System ", tags = "getLoanAccounts")

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Success|OK"),

			@ApiResponse(code = 401, message = "not authorized!"),

			@ApiResponse(code = 403, message = "forbidden!!!"),

			@ApiResponse(code = 404, message = "not found!!!") })

	@GetMapping(value = "/getLoanAccount")
	public List<LoanAccount> getLoanAccounts();

	@ApiOperation(value = "Create LoanAccount in the System ", tags = "Create LoanAccount")

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Success|OK"), @ApiResponse(code = 201, message = "Success|Created"),

			@ApiResponse(code = 401, message = "not authorized!"),

			@ApiResponse(code = 403, message = "forbidden!!!"),

			@ApiResponse(code = 404, message = "not found!!!") })

	@PostMapping(value = "/save/employee")
	public LoanAccount saveLoanAccount(@RequestBody LoanAccount loanAccount);

	@ApiOperation(value = "Update LoanAccount in the System ", tags = "Update LoanAccount")

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Success|OK"), @ApiResponse(code = 201, message = "Success|Created"),

			@ApiResponse(code = 401, message = "not authorized!"),

			@ApiResponse(code = 403, message = "forbidden!!!"),

			@ApiResponse(code = 404, message = "not found!!!") })

	@PostMapping(value = "/update/employee/{loanId}")
	public LoanAccount updateLoanAccount(@RequestBody LoanAccount loanAccount,
			@PathVariable(value = "loanId") Integer loanId);

	@ApiOperation(value = "Get employee in the System ", tags = "getLoanAccount")

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Success|OK"),

			@ApiResponse(code = 401, message = "not authorized!"),

			@ApiResponse(code = 403, message = "forbidden!!!"),

			@ApiResponse(code = 404, message = "not found!!!") })

	@GetMapping(value = "/loanAccount")
	public Response getLoanAccount(@QueryParam(value = "loanId") Integer loanId);

}
