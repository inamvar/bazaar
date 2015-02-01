package com.kalatag.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.domain.Comment;
import com.kalatag.service.CommentService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
//	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommentService commentService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping( method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("title", messageSource.getMessage("admin.home.title", null,locale) );
		List<Comment> comments = commentService.findAccepted(false);
		
		model.addAttribute("comments", comments);
		model.addAttribute("commentsCount", comments.size());
		return "index";
	}
	
	
	@RequestMapping(value = "/acceptComment", method = RequestMethod.GET)
	public String acceptComment(@RequestParam("id") Integer id,
			@RequestParam("accept") Integer accept, Locale locale, Model uiModel) {
		boolean accepted = false;
		if (accept == 1)
			accepted = true;
		else
			accepted = false;

		commentService.changeAccept(id, accepted);
		return "redirect:";
	}
	
	@RequestMapping(value = "/acceptAllComments", method = RequestMethod.GET)
	public String acceptAllComments( Locale locale, Model uiModel) {
		
		commentService.acceptAllComments();
		return "redirect:";
	}

	
}
