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

import com.dariksoft.bazaar.domain.Province;
import com.dariksoft.bazaar.service.ProvinceService;

@Controller
@RequestMapping(value = "/admin/province")
public class ProvinceController {

	@Autowired
	ProvinceService provinceService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("provinces", provinceService.findAll());
		model.put("title", messageSource.getMessage(
				"admin.menu.definitions.provinces", null, locale));
		return new ModelAndView("province/list", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addForm(@ModelAttribute("command") Province province,
			BindingResult result, Locale locale) {

		ModelAndView mv = new ModelAndView("province/add");
		mv.addObject("title", messageSource.getMessage(
				"province.insert.message", null, locale));
		mv.addObject("province", new Province());
		mv.addObject("title", "Add province");

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("command") Province province,
			BindingResult result, Locale locale) {
		Map<String, Object> model = new HashMap<String, Object>();
		if (result.hasErrors()) {

			model.put("errors", result.getAllErrors());
			model.put("title", messageSource.getMessage(
					"province.update.message", null, locale));
			return "province/add";
		}
		provinceService.create(province);

		return "redirect:/admin/province";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id, Locale locale) {
		ModelAndView modelAndView = new ModelAndView("province/update");
		Province province = provinceService.find(id);
		modelAndView.addObject("province", province);
		modelAndView.addObject("title", messageSource.getMessage(
				"province.update.message", null, locale));
		return modelAndView;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String edditingTeam(@ModelAttribute("command") Province province,
			@PathVariable Integer id) {

		provinceService.update(province);

		return "redirect:/admin/province";
	}

	@RequestMapping(value = "/delete/{id}")
	public String add(@PathVariable int id) {

		provinceService.delete(id);

		return "redirect:/admin/province";
	}

}
