package com.micaelpapa.rest.login.model.dao;

import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "appuser")
public class AppUser {
	private String appuserid;
	private String firstname;
	private String lastname;
	private String mail;
	private String password;
	private ZonedDateTime created;
	private ZonedDateTime modified;
	private ZonedDateTime lastlogin;
	private String token;
	private List<Phone> phones;
	private boolean isactive;

	public AppUser() {
	}

	@Id
	public String getAppuserid() {
		return appuserid;
	}

	public void setAppuserid(String appuserid) {
		this.appuserid = appuserid;
	}

	@ElementCollection
	@OneToMany(mappedBy = "appuserid")
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public ZonedDateTime getModified() {
		return modified;
	}

	public void setModified(ZonedDateTime modified) {
		this.modified = modified;
	}

	public ZonedDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(ZonedDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
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
