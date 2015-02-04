package com.offeronline.security;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.web.savedrequest.SavedRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String redirectAdminUrl = "";
    private String redirectMerchantUrl = "";
    private String redirectCustomerUrl = "";
    
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
	  
	   HttpSession session = request.getSession(false);
	    String targetUrl = determineTargetUrl(authentication);
	    if(session != null) {
	        SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
	        if(savedRequest != null) {
	            targetUrl = savedRequest.getRedirectUrl();
	        }
	    }
       
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	

 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
		boolean isMerchant = false;
		boolean isCustomer = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_MERCHANT")) {
                isMerchant = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                isCustomer = true;
                break;
            }
        }
 
        if (isUser) {
            return "/";
        } else if (isAdmin) {
            return this.redirectAdminUrl;
        }else if (isMerchant) {
            return this.redirectMerchantUrl;
        }else if (isCustomer) {
            return this.redirectCustomerUrl;
        }
		else {
        	 return "/";
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

	public String getRedirectAdminUrl() {
		return redirectAdminUrl;
	}

	public void setRedirectAdminUrl(String redirectAdminUrl) {
		this.redirectAdminUrl = redirectAdminUrl;
	}

	public String getRedirectMerchantUrl() {
		return redirectMerchantUrl;
	}

	public void setRedirectMerchantUrl(String redirectMerchantUrl) {
		this.redirectMerchantUrl = redirectMerchantUrl;
	}

	public String getRedirectCustomerUrl() {
		return redirectCustomerUrl;
	}

	public void setRedirectCustomerUrl(String redirectCustomerUrl) {
		this.redirectCustomerUrl = redirectCustomerUrl;
	}
}