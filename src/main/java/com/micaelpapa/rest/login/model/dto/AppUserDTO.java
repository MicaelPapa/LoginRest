package com.micaelpapa.rest.login.model.dto;

import java.util.ArrayList;
import javax.validation.constraints.NotEmpty;
import com.micaelpapa.rest.login.model.dao.Phone;
import com.micaelpapa.rest.login.utils.PasswordFormat;
import com.micaelpapa.rest.login.utils.PasswordMatches;
import com.micaelpapa.rest.login.utils.ValidEmail;
import com.sun.istack.NotNull;

@PasswordMatches
@PasswordFormat
public class AppUserDTO {
	@NotNull
	@NotEmpty
	private String firstname;
	@NotNull
	@NotEmpty
	private String lastname;
	@ValidEmail
	@NotNull
	@NotEmpty
	private String mail;
	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;
	private String token;
	private boolean isActive;
	private ArrayList<PhoneDTO> phones;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public ArrayList<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<PhoneDTO> phones) {
		this.phones = phones;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
