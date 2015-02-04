package com.offeronline.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.offeronline.domain.Merchant;
import com.offeronline.service.merchant.MerchantService;

@Component
public class MerchantEditor extends PropertyEditorSupport {
private @Autowired MerchantService merchantService;
@Override
public void setAsText(String text){
	Merchant c = merchantService.find(Integer.valueOf(text));
	this.setValue(c);
}

}
