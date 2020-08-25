package com.micaelpapa.rest.login.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.sun.el.parser.ParseException;

@Scope("singleton")
@Service
public class MappingHelperService {
	@Autowired
	private ModelMapper modelMapper;

	public Object map(Object object, Class<? extends Object> Class) throws ParseException {
		return modelMapper.map(object, Class);
	}
}
