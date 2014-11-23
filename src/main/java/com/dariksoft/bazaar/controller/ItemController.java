package com.dariksoft.bazaar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dariksoft.bazaar.domain.Attachment;
import com.dariksoft.bazaar.domain.Item;
import com.dariksoft.bazaar.domain.ItemCategory;
import com.dariksoft.bazaar.domain.Merchant;
import com.dariksoft.bazaar.propertyeditor.ItemCategoryEditor;
import com.dariksoft.bazaar.propertyeditor.MerchantEditor;
import com.dariksoft.bazaar.service.ItemCategoryService;
import com.dariksoft.bazaar.service.ItemService;
import com.dariksoft.bazaar.service.MerchantService;

@Controller
@RequestMapping(value = "/admin/item")
public class ItemController {
	private static final Logger logger = LoggerFactory
			.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;
	@Autowired
	ItemCategoryService categoryService;

	@Autowired
	MerchantService merchantService;

	@Autowired
	private MessageSource messageSource;

	private @Autowired ItemCategoryEditor categoryEditor;
	private @Autowired MerchantEditor merchantEditor;

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ItemCategory.class, this.categoryEditor);
		binder.registerCustomEditor(Merchant.class, this.merchantEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("items", itemService.findAll());
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.items", null, locale));
		return "item/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(@ModelAttribute("item") @Valid Item item,
			BindingResult result, Locale locale, Model uiModel) {

		uiModel.addAttribute("title",
				messageSource.getMessage("item.insert.message", null, locale));
		uiModel.addAttribute("item", new Item());
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("merchants", merchantService.findAll());
		return "item/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("item") @Valid Item item,
			BindingResult result, @RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail, Locale locale,
			Model uiModel) {
		
		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"item.insert.message", null, locale));
			return "item/add";
		}
		List<Attachment> attachments = new ArrayList<Attachment>();
		if (files != null && files.length > 0) {
		
			for (int i = 0; i < files.length; i++) {
				try {
					Attachment att = new Attachment();
					att.setFileName(files[i].getOriginalFilename());
					att.setContentType(files[i].getContentType());
					att.setSize(files[i].getSize());
					att.setName(files[i].getName());
					att.setContent(files[i].getBytes());
					attachments.add(att);
				
				} catch (Exception e) {

				}
			}
 
			item.setImages(attachments);

		} else {

		}
		
		if (thumbnail != null) {
			
			try {
				item.setThumbnail(thumbnail.getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		itemService.create(item);

		return "redirect:/admin/item";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Item item = itemService.find(id);
		uiModel.addAttribute("item", item);
		uiModel.addAttribute("title",
				messageSource.getMessage("item.update.message", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("merchants", merchantService.findAll());
		return "item/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("item") @Valid Item item,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail,
			@PathVariable Integer id, BindingResult result, Model uiModel,
			Locale locale) {

		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"item.update.message", null, locale));
			return "item/update/" + id;
		}
		List<Attachment> deletedImages = new ArrayList<Attachment>();
		List<Attachment> attachments = new ArrayList<Attachment>();
		
		Item originalItem = itemService.find(item.getId());
	
		for (Attachment att : item.getImages()) {
			
			if (att.getName().equals("deleted")) {
				for(Attachment org : originalItem.getImages()){
						if(att.getId() == org.getId()){
							deletedImages.add(org);
							break;
						}
				}

			} 
		}
				
		item.setImages(originalItem.getImages());
		item.getImages().removeAll(deletedImages);
		item.setThumbnail(originalItem.getThumbnail());

		if (files != null && files.length > 0) {
			
			for (int i = 0; i < files.length; i++) {
				try {
					Attachment att = new Attachment();
					att.setFileName(files[i].getOriginalFilename());
					att.setContentType(files[i].getContentType());
					att.setSize(files[i].getSize());
					att.setName(files[i].getName());
					att.setContent(files[i].getBytes());
					attachments.add(att);
					
				} catch (Exception e) {

				}
			}
			item.getImages().addAll(attachments);
		

		} else {

		}
	
		if (thumbnail != null && thumbnail.getSize() > 0) {

			try {
				item.setThumbnail(thumbnail.getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		itemService.update(item);

		return "redirect:/admin/item";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		itemService.delete(id);

		return "redirect:/admin/item";
	}
	
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable int id, Model uiModel) {

		uiModel.addAttribute("item", itemService.find(id));

		return "item/detail";
	}

}
