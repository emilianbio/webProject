package com.aykan.controller.user;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aykan.domain.user.User;
import com.aykan.domain.user.VerificationToken;
import com.aykan.event.OnRegistrationCompleteEvent;
import com.aykan.service.user.UserService;
import com.aykan.service.user.VerificationTokenService;
import com.aykan.spring.validator.UserDtoValidator;
import com.aykan.web.dto.UserDto;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserDtoValidator userDtoValidator;
	
	@RequestMapping(params = "registerUser", method = RequestMethod.GET)
	public ModelAndView registerUser(){
		return new ModelAndView("registration", "userDto", new UserDto());
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("userDto") @Valid UserDto userDto, 
										BindingResult result, HttpServletRequest request, Model model, Locale locale){
		if(result.hasErrors()){
			return new ModelAndView("registration", "userDto", userDto);
		}
		String appUrl = getAppUrl(request);
		User user = userService.registerNewUser(userDto);
		publisher.publishEvent(new OnRegistrationCompleteEvent(appUrl, user, request.getLocale()));
		model.addAttribute("message", messageSource.getMessage("message.regSucc", null, locale));
		return new ModelAndView("tokenResult");
	}

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
	
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	private ModelAndView registerConfirm(@RequestParam String token, Model model, Locale locale){
		String result = verificationTokenService.validateVerificationToken(token);
		
		if(result.equals(TOKEN_VALID)){
			model.addAttribute("message", messageSource.getMessage("message.accountVerified", null, locale));
			return new ModelAndView("redirect:/users/login");
		}
		
		model.addAttribute("message", messageSource.getMessage("auth.message."+result, null, locale));
		
		return new ModelAndView("redirect:/users/badUser");
	}
	
	@RequestMapping(value = "/badUser")
	public String badUser(){
		return "badUser";
	}
	
	@RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.POST)
	public String resendRegistrationToken(@RequestParam String username, Model model, Locale locale, HttpServletRequest request){
		User user = userService.findUserByUsername(username);
		if(user == null){
			model.addAttribute("message", messageSource.getMessage("message.badUsername", null, locale));
			return "redirect:/users/badUser";
		}
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = verificationTokenService.findVerificationTokenByUser(user);
		verificationToken.setToken(token);
		verificationTokenService.updateVerificationToken(verificationToken);
		SimpleMailMessage mailMessage = constructMailContains(token, user,locale,getAppUrl(request));
		javaMailSender.send(mailMessage);
		model.addAttribute("message", messageSource.getMessage("message.resetPasswordEmail", null, locale));
		return "tokenResult";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(userDtoValidator);
		webDataBinder.setAllowedFields("firstName", "lastName", "email", "username","password", "matchingPassword");
	}
	
	private SimpleMailMessage constructMailContains(String token, User user, Locale locale, String appUrl) {

		String url = appUrl+"/register/registrationConfirm?token="+token;
		String email = user.getEmail();
		String subject = "Resend registration tokken";
		String text = messageSource.getMessage("message.resendToken", null, locale) +"\n Confirm link : " + url;
		return constructEmail(text, email, subject);
	}


	private SimpleMailMessage constructEmail(String text, String email, String subject) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setText(text);
		mailMessage.setTo(email);
		mailMessage.setSubject(subject);
		mailMessage.setFrom(env.getProperty("support.email"));
		return mailMessage;
	}

	private String getAppUrl(HttpServletRequest request) {
	
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
}
