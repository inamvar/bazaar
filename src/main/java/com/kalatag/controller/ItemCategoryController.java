package com.kalatag.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kalatag.domain.ItemCategory;
import com.kalatag.service.ItemCategoryService;

@Controller
@RequestMapping(value = "/admin/category")
public class ItemCategoryController {
	@Autowired
	ItemCategoryService categoryService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.categories", null, locale));
		return "category/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Locale locale, Model uiModel) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"category.insert.message", null, locale));
		uiModel.addAttribute("category", new ItemCategory());
		return "category/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("category") @Valid ItemCategory category,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"category.insert.message", null, locale));
			return "category/add";
		}
		categoryService.create(category);

		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		ItemCategory category = categoryService.find(id);
		uiModel.addAttribute("category", category);
		uiModel.addAttribute("title", messageSource.getMessage(
				"category.update.message", null, locale));
		return "category/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("category") @Valid ItemCategory category,
			@PathVariable Integer id, BindingResult result, Locale locale,
			Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"category.update.message", null, locale));
			return "category/update/" + id;
		}
		categoryService.update(category);

		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		categoryService.delete(id);

		return "redirect:/admin/category";
	}

}
