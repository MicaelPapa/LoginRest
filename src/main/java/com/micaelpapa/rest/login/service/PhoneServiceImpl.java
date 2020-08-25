package com.micaelpapa.rest.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micaelpapa.rest.login.model.dao.AppUser;
import com.micaelpapa.rest.login.model.dao.Phone;
import com.micaelpapa.rest.login.model.dto.PhoneDTO;
import com.micaelpapa.rest.login.repository.PhoneRepository;
import com.micaelpapa.rest.login.utils.MappingHelperService;
import com.sun.el.parser.ParseException;

@Service
public class PhoneServiceImpl {
	@Autowired
	PhoneRepository phoneRepository;
	@Autowired
	MappingHelperService mappingHelperService;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public Phone registerPhone(PhoneDTO phoneDTO, AppUser user) {
		Phone phone = null;
		try {
			if (phoneRepository.findPhone(phoneDTO) == null) {
				phone = (Phone) mappingHelperService.map(phoneDTO, Phone.class);
				phone.setAppuserid(user);
				phoneRepository.registerPhone(phone);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		return phone;
	}
}
