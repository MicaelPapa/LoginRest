package com.micaelpapa.rest.login.model.dto;

import com.micaelpapa.rest.login.model.dao.AppUser;

public class AppRestResponse {
	private String error;
	private boolean isSucces;
	private int statusCode;
	private AppUser user;

	public AppRestResponse(String error, boolean isSucces, int statusCode, AppUser user) {
		super();
		this.error = error;
		this.isSucces = isSucces;
		this.statusCode = statusCode;
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isSucces() {
		return isSucces;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setSucces(boolean isSucces) {
		this.isSucces = isSucces;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public enum EStatusCode {
		OK(202), ERROR(203), CONFLICT(204), EXISTING_PHONE_NUMBER_CONFLICT(205), EXISTING_MAIL_CONFLICT(206),
		TOKEN_DO_NOT_MATCH(207), INVALID_USERNAME_OR_PASSWORD(208), NON_EXISTENT_ACCOUNT(209);

		private final Integer value;

		private EStatusCode(final Integer value) {
			this.value = value;
		}

		public Integer value() {
			return this.value;
		}

		public String toString() {
			return value.toString();
		}
	}
}
