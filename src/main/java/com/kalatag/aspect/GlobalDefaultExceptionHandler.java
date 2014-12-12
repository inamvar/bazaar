package com.kalatag.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	 public static final String DEFAULT_ERROR_VIEW = "error";
	 
	 @Autowired
	 private MessageSource messageSource;

	 
	    @ExceptionHandler(value = Exception.class)
	    public ModelAndView defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception {

	        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	            throw e;

	        ModelAndView mav = new ModelAndView();
	        e.printStackTrace();
	        
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsString = sw.toString();
	        
	        mav.addObject("exception",exceptionAsString);
	        mav.addObject("message",e.getLocalizedMessage());
	        mav.addObject("url", req.getRequestURL());
	        
	        mav.setViewName(DEFAULT_ERROR_VIEW);
	        return mav;
	    }
	    

}
