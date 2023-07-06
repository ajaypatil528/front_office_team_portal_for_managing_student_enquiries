package in.ajay.itech.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ajay.itech.binding.LoginForm;
import in.ajay.itech.binding.SignUpForm;
import in.ajay.itech.binding.UnlockForm;
import in.ajay.itech.entity.UserDtlsEntity;
import in.ajay.itech.repo.UserDtlsRepo;
import in.ajay.itech.util.EmailUtils;
import in.ajay.itech.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = 
					userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		
		if(entity == null) {
			return "Invalid Credentials";
		}
		
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		
		//create session and store user data in session 
		session.setAttribute("userId", entity.getUserId());
		
		return "success";
	}

	@Override
	public boolean signUp(SignUpForm form) {
		
		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
		if(user != null) {
			return false;
		}
		
		//TODO: Copy data from binding obj to entity obj.
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		//TODO: Generate random password and set to object
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);
		
		//TODO: set account status as LOCKED
		entity.setAccStatus("LOCKED");
		
		// TODO Insert record
		userDtlsRepo.save(entity);
		
		// TODO send email to user to unlock the account
		String to = form.getEmail();
		String subject = "Unlock your Account";
		//String body = "<h1>Use below temporary password to unlock your account</h1>";
		
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use below temporary password to unlock your account</h1>");
		body.append("Temporary password : "+tempPwd);
		body.append("</br>");
		body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click here to unlock your account</a>");
		emailUtils.sendEmail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {

		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		if(entity.getPwd().equals(form.getTempPwd())) {
			
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("Unlocked");
			userDtlsRepo.save(entity);
			return true;
		} else {
			return false;
		}
		
	}

/*	@Override
	public String forgotPwd(String email) {
		
		// TODO: check record present in DB with given email
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		// TODO: if record not available in DB send error message
		if(entity == null) {
			return "Invalid Email ID";
		}
		// TODO: if record available send password to email and send success message
		
		String subject = "Recover Password";
		String body = "Your password ::"+entity.getPwd();
		
		emailUtils.sendEmail(email, subject, body);
		
		return "Password sent to your mail";
	}
*/	
	
    @Override
	public boolean forgotPwd(String email) {
		
		// TODO: check record present in DB with given email
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		// TODO: if record not available in DB send error message
		if(entity == null) {
			return false;
		}
		
		// TODO: if record available send password to email and send success message
		
		String subject = "Recover Password";
		String body = "Your password ::"+entity.getPwd();
		
		emailUtils.sendEmail(email, subject, body);
		
		return true;
	}

}
