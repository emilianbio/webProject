package com.aykan.controller.user;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aykan.domain.user.PasswordResetToken;
import com.aykan.domain.user.Role;
import com.aykan.domain.user.User;
import com.aykan.security.ActiveUserStore;
import com.aykan.security.LoggedUser;
import com.aykan.security.service.UserSecurityService;
import com.aykan.service.user.PasswordResetTokenService;
import com.aykan.service.user.RoleService;
import com.aykan.service.user.UserService;
import com.aykan.web.dto.PasswordDto;
import com.aykan.web.exceptiýon.InvalidOldPasswordException;
import com.aykan.web.exceptiýon.UserNotFoundException;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private Environment env;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String userList(Model model){
		List<User> userList = userService.findAllUsers();
		model.addAttribute("users", userList);
		return "users";
	}
	
	@RequestMapping(value="/activeUsers", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
	public String onlineUsers(Model model){
		List<LoggedUser> activeUser = ActiveUserStore.getUsers();
		model.addAttribute("activeUsers", activeUser);
		return "activeUsers";
	}
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
	public String viewUser(@PathVariable String username, Model model, Locale locale, Principal principal, HttpServletRequest request){
		System.out.println("Urý : " + request.getRequestURI());
		System.out.println("Url : " + request.getRequestURL());
		User user = userService.findUserByUsername(username);
		if(user == null){
			throw new UserNotFoundException();
		}	
		String status = userStatusControl(username, locale);
		if(username.equals(principal.getName())){
			model.addAttribute("message", "My profile");
			model.addAttribute("user", user);
			return "viewUser";
		}
		model.addAttribute("loginStatus", status);
		model.addAttribute("user", user);
		return "viewUser";
	}

	@RequestMapping(value = "/{username}/editProfile", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
	public String editUser(@PathVariable String username, Model model, Principal principal){
		User user = userService.findUserByUsername(username);
		if(user == null){
			throw new UserNotFoundException();
		}
		getRoles(model);
		model.addAttribute("user", user);
		model.addAttribute("principalName", principal.getName());
		return "editUser";
	}
	
	@RequestMapping(value = "/{username}/editProfile", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, Model model ){
		userService.updateUser(user);
		return "redirect:/users";
	}
	
	
	@RequestMapping(value = "/{username}/sendPasswordToken", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
	public String sendResetPasswordToken(@PathVariable String username, Model model, HttpServletRequest request, Locale locale){
		User user = userService.findUserByUsername(username);
		if(user == null){
			throw new UserNotFoundException();
		}
		String token = UUID.randomUUID().toString();
		SimpleMailMessage mailMessage = constructMailContains(token, user, locale, getAppUrl(request));
		javaMailSender.send(mailMessage);
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
		passwordResetTokenService.savePasswordResetToken(passwordResetToken);
		model.addAttribute("message", messageSource.getMessage("message.resetPasswordEmail", null, locale));
		return "tokenResult";
	}
	
	//auth.message.invalidToken=Invalid token.
	//auth.message.expired=Your registration token has expired. Please register again.
	
	@RequestMapping(value = "resetPassword", method = RequestMethod.GET)
	public String editUserPassword(@RequestParam String token, @RequestParam long id, Model model, Locale locale){
		String result = userSecurityService.validatePasswordResetToken(token, Long.valueOf(id));
		if (!result.equals("valid")) {
			model.addAttribute("message", messageSource.getMessage("auth.message."+result, null, locale));
			return "redirect:/users/login";
		}
		model.addAttribute("passwordDto", new PasswordDto());
		return "editPassword";
	}
	
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_READ_PRIVILEGE')")
	public String changeUserPassword(@ModelAttribute("passwordDto") @Valid PasswordDto passwordDto, Principal principal, 
					Model model, Locale locale, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			model.addAttribute("passwordDto", passwordDto);
			return "editPassword";
		}
		User user = userService.findUserByUsername(principal.getName());
		boolean result = userService.checkIfValidOldPassword(user, passwordDto.getOldPassword());
		if(!result){
			throw new InvalidOldPasswordException();
		}
		userService.changeUserPassword(user, passwordDto.getNewPassword());
		model.addAttribute("message", messageSource.getMessage("message.resetPasswordSuc", null, locale));
		return "redirect:/users/"+principal.getName();
	}
	
	//Veritabandan rolleri cekiyoruz.
	private void getRoles(Model model){
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);
	}
	
	//Online olup olmadýgýný kontrol ediyoruz.
	private String userStatusControl(String username, Locale locale) {
		for (LoggedUser loggedUser : ActiveUserStore.getUsers()) {
			if(loggedUser.getUsername().equals(username)){
				return messageSource.getMessage("message.status.online", null, locale);
			}
		}
		return messageSource.getMessage("message.status.ofline", null, locale);
	}
	
	private SimpleMailMessage constructMailContains(String token, User user, Locale locale, String appUrl) {
		String url = appUrl+"/users/resetPassword?token="+token+"&id="+user.getId();
		String email = user.getEmail();
		String subject = messageSource.getMessage("message.resetPassword", null, locale);
		String text = messageSource.getMessage("message.password.confirm.text", null, locale) + "\n" +  url;
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
