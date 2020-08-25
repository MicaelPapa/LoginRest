package com.micaelpapa.rest.login.model.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
	@Column(name = "phoneid")
	private Long phoneid;
	@Column(name = "number")
	private String number;
	@Column(name = "citycode")
	private String citycode;
	@Column(name = "countrycode")
	private String countrycode;
	@Column(name = "appuserid")
	private AppUser appuserid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(Long phoneid) {
		this.phoneid = phoneid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appuserid")
	public AppUser getAppuserid() {
		return appuserid;
	}

	public void setAppuserid(AppUser appuserid) {
		this.appuserid = appuserid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
}
