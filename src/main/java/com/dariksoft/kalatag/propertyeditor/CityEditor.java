package com.dariksoft.kalatag.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariksoft.kalatag.domain.City;
import com.dariksoft.kalatag.service.CityService;
@Component
public class CityEditor extends PropertyEditorSupport {
	private @Autowired CityService cityService;
	@Override
	public void setAsText(String text){
		City c = cityService.find(Integer.valueOf(text));
		this.setValue(c);
	}

}
