package com.micaelpapa.rest.login.utils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.micaelpapa.rest.login.model.dto.AppUserDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		AppUserDTO user = (AppUserDTO) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}
}
