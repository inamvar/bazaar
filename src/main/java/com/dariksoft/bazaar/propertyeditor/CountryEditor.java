package com.dariksoft.bazaar.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariksoft.bazaar.domain.Country;
import com.dariksoft.bazaar.service.CountryService;

@Component
public class CountryEditor extends PropertyEditorSupport {
private @Autowired CountryService countryService;
@Override
public void setAsText(String text){
	Country c = countryService.find(Integer.valueOf(text));
	this.setValue(c);
}

}
