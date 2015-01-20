package com.kalatag.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.service.CommentService;

@Controller
@RequestMapping(value = "/admin/comment")
public class CommentController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("comments",
				commentService.findAll("isAccepted", "ASC"));
		uiModel.addAttribute("title",
				messageSource.getMessage("comment.list", null, locale));
		return "comment/list";
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changeAccepted(@RequestParam("id") Integer id,
			@RequestParam("accept") Integer accept, Locale locale, Model uiModel) {
		boolean accepted = false;
		if (accept == 1)
			accepted = true;
		else
			accepted = false;

		commentService.changeAccept(id, accepted);
		return "redirect:list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id, Locale locale,
			Model uiModel) {
		commentService.delete(id);
		return "redirect:list";
	}
}
