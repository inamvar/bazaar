package com.kalatag.controller;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class RobotsController {

    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	@ResponseBody
    public String getRobots(HttpServletRequest request) {

    	String newLine = System.getProperty("line.separator");
    	StringBuilder sb = new StringBuilder();
    	sb.append("User-agent: *");
    	sb.append(newLine);
    	sb.append("Disallow: /cgi-bin/");
    	sb.append(newLine);
    	sb.append("Disallow: /admin/");
    	sb.append(newLine);
    	sb.append("Disallow: /merchant/");
    	sb.append(newLine);
    	sb.append("Disallow: /customer/");
    	sb.append(newLine);
    	sb.append("Disallow: /files/coupons/");
        return (Arrays.asList("kalatag.com", "www.kalatag.com").contains(request.getServerName())) ?
                sb.toString() : "robotsDisallowed";
    }
}