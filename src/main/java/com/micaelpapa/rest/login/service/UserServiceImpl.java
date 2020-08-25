package com.micaelpapa.rest.login.service;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.micaelpapa.rest.login.model.dao.AppUser;
import com.micaelpapa.rest.login.model.dao.Phone;
import com.micaelpapa.rest.login.model.dto.AppRestResponse;
import com.micaelpapa.rest.login.model.dto.AppUserDTO;
import com.micaelpapa.rest.login.model.dto.PhoneDTO;
import com.micaelpapa.rest.login.repository.UserRepository;
import com.micaelpapa.rest.login.utils.MappingHelperService;
import com.micaelpapa.rest.login.utils.PasswordGenerator;
import com.sun.el.parser.ParseException;

@Service
public class UserServiceImpl {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PhoneServiceImpl phoneServiceImpl;
	@Autowired
	MappingHelperService mappingHelperService;
	@Autowired
	ApplicationContext context;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public AppRestResponse registerUserHandler(AppUserDTO userDTO) {
		try {
			return registerUserImpl(userDTO);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new AppRestResponse(e.getMessage(), false, AppRestResponse.EStatusCode.ERROR.value(), null);
		}
	}

	@Transactional(rollbackOn = { RuntimeException.class })
	private AppRestResponse registerUserImpl(AppUserDTO userDTO) throws RuntimeException {
		AppUser user = null;
		try {
			user = completeFieldsNewUser(userDTO);
			if (userRepository.findAppUser(user.getMail()) == null)
				userRepository.persistAppUser(user);
			else {
				throw new RuntimeException("That email is already used.");
			}
			if (!createPhonesForSpecificUser(userDTO.getPhones(), user)) {
				throw new RuntimeException(
						"One or more of the phone numbers to be registered already exists in database.");
			}
		} catch (PersistenceException | ParseException e) {
			logger.error(e.getMessage());
			return new AppRestResponse(e.getMessage(), false, AppRestResponse.EStatusCode.ERROR.value(), null);
		}
		return new AppRestResponse(null, true, AppRestResponse.EStatusCode.OK.value(), user);
	}

	public AppRestResponse logIn(String mail, String pass, String token) {
		AppUser user = userRepository.findAppUser(mail);
		try {
			if (user == null)
				return new AppRestResponse("There is no user registered with that email", false,
						AppRestResponse.EStatusCode.NON_EXISTENT_ACCOUNT.value(), null);
			else if (!context.getBean(PasswordGenerator.class).encodePassword(pass).equals(user.getPassword()))
				return new AppRestResponse("Invalid username or password.", false,
						AppRestResponse.EStatusCode.INVALID_USERNAME_OR_PASSWORD.value(), null);
			else if (!user.getToken().contentEquals(token))
				return new AppRestResponse("Invalid token", false,
						AppRestResponse.EStatusCode.TOKEN_DO_NOT_MATCH.value(), null);
		} catch (BeansException | UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return new AppRestResponse("Internal server error", false, AppRestResponse.EStatusCode.ERROR.value(), null);
		}
		return new AppRestResponse(null, true, AppRestResponse.EStatusCode.OK.value(), user);
	}

	private AppUser completeFieldsNewUser(AppUserDTO userDTO) throws ParseException {

		try {
			AppUser user = (AppUser) mappingHelperService.map(userDTO, AppUser.class);
			user.setCreated(ZonedDateTime.now());
			user.setLastlogin(ZonedDateTime.now());
			user.setModified(ZonedDateTime.now());
			user.setAppuserid(UUID.randomUUID().toString());
			user.setCreated(ZonedDateTime.now());
			user.setPassword(context.getBean(PasswordGenerator.class).encodePassword(user.getPassword()));
			user.setPhones(null);
			user.setToken(UUID.randomUUID().toString());
			return user;
		} catch (BeansException | UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	private boolean createPhonesForSpecificUser(List<PhoneDTO> phoneDTOList, AppUser user) {
		ArrayList<Phone> phoneList = new ArrayList<Phone>();
		for (PhoneDTO phoneDTO : phoneDTOList) {
			Phone phoneHelper = phoneServiceImpl.registerPhone(phoneDTO, user);
			if (phoneHelper != null)
				phoneList.add(phoneHelper);
			else
				return false;
		}
		user.setPhones(phoneList);
		return true;
	}
}