package DuckHunt.Request;

import DuckHunt.Constant.Responses;

import java.io.Serializable;

public class Response implements Serializable {

	private Responses status;
	private String errorMessage;

	public Response(Responses status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
	
	public Responses getStatus() {
		return status;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
}