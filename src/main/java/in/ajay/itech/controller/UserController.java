package in.ajay.itech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ajay.itech.binding.LoginForm;
import in.ajay.itech.binding.SignUpForm;
import in.ajay.itech.binding.UnlockForm;
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
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		
		String status = userService.login(loginForm);
		
		if(status.contains("success")) {
			//redirect request to dashboard method
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", status);
		return "login";
	}
	
	
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		
		//Check the new password and confirm password is same
		
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())){
				
			boolean status = userService.unlockAccount(unlock);
				if(status) {
					model.addAttribute("succMsg","Your account is unlocked successfully");
				}else {
					model.addAttribute("errMsg","Given temporay password is incorrect, check your email");
				}
		}else {
				model.addAttribute("errMsg","New password and confirm password should be same");
		}
		return "unlock";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);
		model.addAttribute("unlock", unlockFormObj);
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email, Model model) {
		
		System.out.println(email);
		
		boolean status = userService.forgotPwd(email);
		
		if(status) {
			model.addAttribute("succMsg", "Password sent to your email");
		}else {
			model.addAttribute("errMsg", "Invalid email");
		}
			return "forgotPwd";
	}


}
