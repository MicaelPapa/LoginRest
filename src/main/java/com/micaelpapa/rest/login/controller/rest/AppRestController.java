package com.micaelpapa.rest.login.controller.rest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.micaelpapa.rest.login.controller.AppController;
import com.micaelpapa.rest.login.model.dto.AppRestResponse;
import com.micaelpapa.rest.login.model.dto.AppUserDTO;

@RestController
public class AppRestController {
	@Autowired
	AppController appController;

	@PostMapping(value = "/user/register", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<AppRestResponse> register(@RequestBody @Valid AppUserDTO user, Errors errors) {
		if (errors.hasErrors()) {
			return appController.composeResponse(new AppRestResponse(errors.getAllErrors().get(0).getDefaultMessage(),
					false, AppRestResponse.EStatusCode.CONFLICT.value(), null));
		}
		return appController.registerUser(user);
	}

	@PostMapping(value = "/user/login", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<AppRestResponse> logIn(@RequestParam("email") String email,
			@RequestParam("pass") String pass, @RequestParam("token") String token) {
		return appController.logIn(email, pass, token);
	}
}
