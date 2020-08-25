package com.micaelpapa.rest.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.micaelpapa.rest.login.model.dto.AppRestResponse;
import com.micaelpapa.rest.login.model.dto.AppUserDTO;
import com.micaelpapa.rest.login.service.UserServiceImpl;

@Controller
public class AppController {
	@Autowired
	UserServiceImpl userServiceImpl;

	public ResponseEntity<AppRestResponse> registerUser(AppUserDTO user) {
		return composeResponse(userServiceImpl.registerUserHandler(user));
	}

	public ResponseEntity<AppRestResponse> logIn(String email, String pass, String token) {
		AppRestResponse response = userServiceImpl.logIn(email, pass, token);
		return composeResponse(response);
	}

	private HttpStatus getCorrespondingHttpStatus(AppRestResponse response) {
		if (response.getStatusCode() == AppRestResponse.EStatusCode.OK.value())
			return HttpStatus.OK;
		else if (response.getStatusCode() == AppRestResponse.EStatusCode.EXISTING_MAIL_CONFLICT.value()
				|| response.getStatusCode() == AppRestResponse.EStatusCode.EXISTING_PHONE_NUMBER_CONFLICT.value()
				|| response.getStatusCode() == AppRestResponse.EStatusCode.TOKEN_DO_NOT_MATCH.value()
				|| response.getStatusCode() == AppRestResponse.EStatusCode.INVALID_USERNAME_OR_PASSWORD.value()
				|| response.getStatusCode() == AppRestResponse.EStatusCode.NON_EXISTENT_ACCOUNT.value())
			return HttpStatus.CONFLICT;
		else
			return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ResponseEntity<AppRestResponse> composeResponse(AppRestResponse response) {
		return new ResponseEntity<AppRestResponse>(response, getCorrespondingHttpStatus(response));
	}
}