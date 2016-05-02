package com.formation.computerdatabase.webapp.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller used for login and logout.
 * @author Cédric Cousseran
 *
 */
@Controller
public class LoginController {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  /**
   * Login a user.
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(@RequestParam Map<String,String> requestParams, Model model) {

    if (requestParams.get("error") != null) {
    	model.addAttribute("error", true);
    }
    /*
    if (logout != null) {
      model.addObject("msg", true);
    }
    */
    return "login";
  }

  /**
   * Logout a user.
   */
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    LOGGER.info("An user just disconnected");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    new SecurityContextLogoutHandler().logout(request, response, auth);
    return "redirect:/login?logout";
    
  }
  
}