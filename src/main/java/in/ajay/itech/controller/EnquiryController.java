package in.ajay.itech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage() {
		return "add-enquiry";
	}
	@GetMapping("/enquiries")
	public String viewEnquiresPage() {
		return "viewenquiries";
	}
}
