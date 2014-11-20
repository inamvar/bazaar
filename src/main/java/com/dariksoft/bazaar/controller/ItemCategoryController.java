package com.dariksoft.bazaar.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dariksoft.bazaar.domain.ItemCategory;
import com.dariksoft.bazaar.service.ItemCategoryService;

@Controller
@RequestMapping(value="/admin/category")
public class ItemCategoryController {
	@Autowired
	ItemCategoryService categoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(Locale locale){
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 
		  model.put("categories",  categoryService.findAll());  
		  model.put("title", messageSource.getMessage("admin.menu.definitions.categories", null,locale));
		  return new ModelAndView("category/list", model);  	
	}
	
	 @RequestMapping(value = "/add", method = RequestMethod.GET)  
	 public ModelAndView addForm(@ModelAttribute("command")ItemCategory category,  
	   BindingResult result, Locale locale) {  
		 
		 ModelAndView mv = new ModelAndView("category/add");
		 mv.addObject("title",  messageSource.getMessage("category.insert.message", null,locale));
		 mv.addObject("category",new ItemCategory());
		 		 
	  return mv;  
	 }  
	 		 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)  
	 public String add(@ModelAttribute("command")ItemCategory category,  
	   BindingResult result, Locale locale) {  
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 if(result.hasErrors()){
			
			 model.put("errors", result.getAllErrors());
			 model.put("title",  messageSource.getMessage("category.update.message", null,locale));
			 return "category/add";
		 }
		 categoryService.create(category);
	 	 
		 	return "redirect:/admin/category";  
	 }
	 
	 
		@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
		public ModelAndView updateFrom(@PathVariable Integer id,Locale locale) {
			ModelAndView modelAndView = new ModelAndView("category/update");
			ItemCategory category = categoryService.find(id);			
			modelAndView.addObject("category",category);
			modelAndView.addObject("title",  messageSource.getMessage("category.update.message", null,locale));
			return modelAndView;
		}

		@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
		public String update(@ModelAttribute("command") ItemCategory category, @PathVariable Integer id) {

			categoryService.update(category);
			
			return "redirect:/admin/category";
		}
	 
	 
	 @RequestMapping(value = "/delete/{id}")  
	 public String delete(@PathVariable int id) {  
		 
		 categoryService.delete(id);
	 	 
		 	return "redirect:/admin/category";  
	 }
	 
}
