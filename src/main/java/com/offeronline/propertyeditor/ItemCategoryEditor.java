package com.offeronline.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.offeronline.domain.ItemCategory;
import com.offeronline.service.ItemCategoryService;
@Component
public class ItemCategoryEditor extends PropertyEditorSupport {
	private @Autowired ItemCategoryService categoryService;
	@Override
	public void setAsText(String text){
		ItemCategory c = categoryService.find(Integer.valueOf(text));
		this.setValue(c);
	}

}
