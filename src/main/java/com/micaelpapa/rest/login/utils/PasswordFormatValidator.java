package com.micaelpapa.rest.login.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.LowercaseCharacterRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.micaelpapa.rest.login.model.dto.AppUserDTO;

public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, Object> {
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		AppUserDTO user = (AppUserDTO) obj;
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new UppercaseCharacterRule(1),
				new DigitCharacterRule(2), new LowercaseCharacterRule(2)));

		RuleResult result = validator.validate(new PasswordData(user.getPassword()));
		if (result.isValid()) {
			return true;
		}
		return false;
	}
}
