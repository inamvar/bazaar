package com.kalatag.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kalatag.domain.Attachment;
import com.kalatag.domain.Deal;
import com.kalatag.domain.DealOption;
import com.kalatag.domain.ItemCategory;
import com.kalatag.domain.Merchant;
import com.kalatag.propertyeditor.ItemCategoryEditor;
import com.kalatag.propertyeditor.MerchantEditor;
import com.kalatag.service.DealOptionService;
import com.kalatag.service.DealService;
import com.kalatag.service.ItemCategoryService;
import com.kalatag.service.merchant.MerchantService;

@Controller
@RequestMapping(value = "/admin/deal")
public class DealController {
	private static final Logger logger = LoggerFactory
			.getLogger(DealController.class);

	@Autowired
	DealService dealService;
	@Autowired
	ItemCategoryService categoryService;

	@Autowired
	MerchantService merchantService;

	@Autowired
	DealOptionService optionService;

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

		uiModel.addAttribute("deals", dealService.findAll());
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.deals", null, locale));
		return "deal/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(@ModelAttribute("deal") @Valid Deal item,
			BindingResult result, Locale locale, Model uiModel) {

		uiModel.addAttribute("title",
				messageSource.getMessage("deal.insert.message", null, locale));
		Deal deal = new Deal();
		List<DealOption> options = new AutoPopulatingList<DealOption>(
				DealOption.class);
		// options.add(new DealOption());
		deal.setOptions(options);
		uiModel.addAttribute("deal", deal);
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("merchants", merchantService.findAll());
		return "deal/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("deal") @Valid Deal item,
			BindingResult result, @RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail, Locale locale,
			Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("merchants", merchantService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"deal.insert.message", null, locale));
			return "deal/add";
		}

		logger.debug("options count:" + item.getOptions().size());
		for (DealOption option : item.getOptions()) {

			logger.debug("Option id:" + option.getId());
			logger.debug("Option name:" + option.getName());
			logger.debug("Option discount:" + option.getDiscount());
		}
		manageOptions(item);

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
		dealService.create(item);

		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Deal deal = dealService.find(id);

		uiModel.addAttribute("deal", deal);
		uiModel.addAttribute("title",
				messageSource.getMessage("deal.update.message", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("merchants", merchantService.findAll());
		return "deal/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("deal") @Valid Deal item,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail,
			@PathVariable Integer id, BindingResult result, Model uiModel,
			Locale locale) {

		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"deal.update.message", null, locale));
			return "deal/update/" + id;
		}
		List<Attachment> deletedImages = new ArrayList<Attachment>();
		List<Attachment> attachments = new ArrayList<Attachment>();

		Deal originalItem = dealService.find(item.getId());

		if (item.getImages() != null) {
			for (Attachment att : item.getImages()) {

				if (att.getName().equals("deleted")) {
					for (Attachment org : originalItem.getImages()) {
						if (att.getId() == org.getId()) {
							deletedImages.add(org);
							break;
						}
					}

				}
			}
		}

		if (originalItem.getImages() != null)
			item.setImages(originalItem.getImages());
		if (deletedImages != null && deletedImages.size() > 0)
			item.getImages().removeAll(deletedImages);
		if (originalItem.getThumbnail() != null)
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

		List<DealOption> options2remove = manageOptions(item);

		// First, save the deal
		dealService.update(item);
		// Then, delete the previously linked options which should be now
		// removed
		for (DealOption option : options2remove) {
			logger.debug("option.id="+ option.getId());
			if (option.getId() > 0) {
				optionService.delete(option.getId());
			}
		}

		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		dealService.delete(id);

		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable int id, Model uiModel) {

		uiModel.addAttribute("deal", dealService.find(id));

		return "deal/detail";
	}

	// Manage dynamically added or removed options
	private List<DealOption> manageOptions(Deal deal) {
		// Store the employees which shouldn't be persisted
		List<DealOption> options2remove = new ArrayList<DealOption>();
		if (deal.getOptions() != null) {
			for (Iterator<DealOption> i = deal.getOptions().iterator(); i
					.hasNext();) {
				DealOption option = i.next();
				// If the remove flag is true, remove the option from the list
				if (option.getRemove() == 1) {
					options2remove.add(option);
					i.remove();
					// Otherwise, perform the links
				} else {
					option.setDeal(deal);
				}
			}
		}
		return options2remove;
	}

}
