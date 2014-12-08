package com.kalatag.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kalatag.domain.Province;
import com.kalatag.service.ProvinceService;
@Component
public class ProvinceEditor extends PropertyEditorSupport {
	private @Autowired ProvinceService provinceService;
	@Override
	public void setAsText(String text){
		Province c = provinceService.find(Integer.valueOf(text));
		this.setValue(c);
	}

}
