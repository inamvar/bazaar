package com.kalatag.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kalatag.domain.City;
import com.kalatag.service.CityService;
@Component
public class CityEditor extends PropertyEditorSupport {
	private @Autowired CityService cityService;
	@Override
	public void setAsText(String text){
		City c = cityService.find(Integer.valueOf(text));
		this.setValue(c);
	}

}
