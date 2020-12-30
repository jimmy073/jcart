package com.izasoft.jcart.web.contoller;



import static com.izasoft.jcart.web.utils.MessageCodes.ERROR_INVALID_PASSWORD_RESET_REQUEST;
import static com.izasoft.jcart.web.utils.MessageCodes.ERROR_PASSWORD_CONF_PASSWORD_MISMATCH;
import static com.izasoft.jcart.web.utils.MessageCodes.INFO_PASSWORD_RESET_LINK_SENT;
import static com.izasoft.jcart.web.utils.MessageCodes.INFO_PASSWORD_UPDATED_SUCCESS;
import static com.izasoft.jcart.web.utils.MessageCodes.LABEL_PASSWORD_RESET_EMAIL_SUBJECT;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.izasoft.jcart.JCartException;
import com.izasoft.jcart.common.service.EmailService;
import com.izasoft.jcart.common.service.JCLogger;
import com.izasoft.jcart.security.SecurityService;
import com.izasoft.jcart.web.utils.WebUtils;
@Controller
public class UserAuthController extends JCartAdminBaseContoller {

	@Autowired protected SecurityService securityService;
	@Autowired protected EmailService emailService;
	@Autowired protected TemplateEngine templateEngine;
	@Autowired protected PasswordEncoder passwordEncoder;
	
	private static final JCLogger logger = JCLogger.getLogger(UserAuthController.class);
	
	@RequestMapping(value = "/forgotPwd", method = RequestMethod.POST)
	public String handleForgotPwd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String email = request.getParameter("email");
		try {
			//generates a token (UUID.randomUUID().toString()) and store it in USERS.PASSWORD_RESET_TOKEN column.
		String token = securityService.resetPassword(email);
		String resetPwdURL = WebUtils.getURLWithCotextPath(request)+"resetPwd?email="
				+email+"&token"+token;
		this.sendForgotPasswordEmail(email, resetPwdURL);
		redirectAttributes.addFlashAttribute("msg", getMessage(INFO_PASSWORD_RESET_LINK_SENT));
		} catch (JCartException e) {
			logger.error(e);
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
		}
		return "redirect:/forgotPwd"; 
	}

	private void sendForgotPasswordEmail(String email, String resetPwdURL) {
		try {
//			String htmlContent= "Please click this link to reset your password <br/>"+resetPwdURL;
//			emailService.sendEmail(email, getMessage(LABEL_PASSWORD_RESET_EMAIL_SUBJECT), htmlContent);		
		
			//Prepare Evaluation Context
			final Context ctx = new Context();
			ctx.setVariable("resetPwdURL", resetPwdURL);
			
			//Create HTML Body using Thymeleaf
			final String htmlContent = this.templateEngine.process("forgot-password-email",ctx);
			
			emailService.sendEmail(email, getMessage(LABEL_PASSWORD_RESET_EMAIL_SUBJECT), htmlContent);
			
		} catch (JCartException e) {
			logger.error(e);
		}
	}
	
	
	@RequestMapping(value="/resetPwd", method = RequestMethod.GET)
	public String resetPwd(HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		
		boolean valid = securityService.verifyPasswordResetToken(email, token);
		
		if(valid) {
			model.addAttribute("email",email);
			model.addAttribute("token",token);
			return "public/resetPwd";
		} else {
			redirectAttributes.addFlashAttribute("msg", getMessage(ERROR_INVALID_PASSWORD_RESET_REQUEST));
			return "redirect:/login";
		}
	
	}
	
	@RequestMapping(value = "/resetPwd", method=RequestMethod.POST)
	public String handleResetPwd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String email = request.getParameter("email");
			String token = request.getParameter("token");
			String password = request.getParameter("password");
			String confPassword = request.getParameter("confPassword");
			
			if(!password.equals(confPassword)) {
				redirectAttributes.addAttribute("email", email);
				redirectAttributes.addAttribute("token", token);
				redirectAttributes.addAttribute("msg", getMessage(ERROR_PASSWORD_CONF_PASSWORD_MISMATCH));
				return "public/resetPwd";
			}
			
			String encodedPwd = passwordEncoder.encode(password);
			securityService.updatePassword(email, token, encodedPwd);
			redirectAttributes.addAttribute("msg", getMessage(INFO_PASSWORD_UPDATED_SUCCESS));
		} catch (JCartException e) {
			logger.error(e);
			redirectAttributes.addAttribute("msg", getMessage(ERROR_INVALID_PASSWORD_RESET_REQUEST));
		}
		return "redirect:/login";
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
