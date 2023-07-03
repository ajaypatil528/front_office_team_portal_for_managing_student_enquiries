package in.ajay.itech.service;

import in.ajay.itech.binding.LoginForm;
import in.ajay.itech.binding.SignUpForm;
import in.ajay.itech.binding.UnlockForm;

public interface UserService {

	public String login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String forgotPwd(String email);
}
