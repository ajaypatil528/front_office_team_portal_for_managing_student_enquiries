package in.ajay.itech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ajay.itech.binding.SignUpForm;
import in.ajay.itech.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signUp(form);
		if(status) {
			model.addAttribute("succMesg", "Account created, Check your email");
		}else{
			model.addAttribute("errMesg","Choose Unique email");
		}
		return "signup";
	}
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/unlock")
	public String unlockPage() {
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}


}
