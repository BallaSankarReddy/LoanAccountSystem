package com.loan.common.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ValidationException>{

	@Override
	public Response toResponse(ValidationException exception) {

		
			String errorCode = exception.getErrorCode();
			return Response.ok(exception.getErrorMessage()).type(MediaType.APPLICATION_JSON).build();
			//(exception.getMessage()).build();
	}

}
