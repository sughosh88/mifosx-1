package org.mifosplatform.batch.command.internal;

import org.mifosplatform.batch.command.CommandStrategy;
import org.mifosplatform.batch.domain.BatchRequest;
import org.mifosplatform.batch.domain.BatchResponse;
import org.mifosplatform.batch.exception.ErrorHandler;
import org.mifosplatform.batch.exception.ErrorInfo;
import org.mifosplatform.portfolio.client.api.ClientsApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implements {@link org.mifosplatform.batch.command.CommandStrategy} to handle
 * creation of a new client. It passes the contents of the body from the BatchRequest
 * to {@link org.mifosplatform.portfolio.client.api.ClientsApiResource} and gets back
 * the response. This class will also catch any errors raised by 
 * {@link org.mifosplatform.portfolio.client.api.ClientsApiResource} and map those errors
 * to appropriate status codes in BatchResponse.
 * 
 * @author Rishabh Shukla
 * 
 * @see org.mifosplatform.batch.command.CommandStrategy
 * @see org.mifosplatform.batch.domain.BatchRequest
 * @see org.mifosplatform.batch.domain.BatchResponse
 */
@Component
public class CreateClientCommandStrategy implements CommandStrategy{

	private final ClientsApiResource clientsApiResource;
	
	@Autowired
	public CreateClientCommandStrategy(final ClientsApiResource clientsApiResource) {
		this.clientsApiResource = clientsApiResource;
	}	
	
	@Override
	public BatchResponse execute(final BatchRequest request) {
		
		final BatchResponse response = new BatchResponse();	
		final String responseBody;		

		response.setRequestId(request.getRequestId());
		response.setHeaders(request.getHeaders());
		
		//Try-catch blocks to map exceptions to appropriate status codes
		try {
			
			//Calls 'create' function from 'ClientsApiResource' to create a new client
			responseBody = clientsApiResource.create(request.getBody());
			
			response.setStatusCode(200);
			//Sets the body of the response after the successful creation of the client
			response.setBody(responseBody);
			
		}
		catch (RuntimeException e) {
			
			//Gets an object of type ErrorInfo, containing information about raised exception
			ErrorInfo ex = ErrorHandler.handler(e);
			
			response.setStatusCode(ex.getStatusCode());
			response.setBody(ex.getMessage());
		}
		
		return response;		
	}

}
