package in.ajay.itech.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ajay.itech.binding.DashboardResponse;
import in.ajay.itech.binding.EnquiryForm;
import in.ajay.itech.binding.EnquirySearchCriteria;
import in.ajay.itech.constants.AppConstants;
import in.ajay.itech.entity.StudentEnqEntity;
import in.ajay.itech.service.EnquiryService;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		//TODO:logic to fetch the data for the dashboard page
		
		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);
		DashboardResponse dashboardData = enqService.getDashboardData(userId);
		model.addAttribute("dashboardData", dashboardData);
		
		return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		
		//System.out.println(formObj);
		
		//TODO: save the data
		boolean status = enqService.saveEnquiry(formObj);
		if(status) {
			model.addAttribute("succMsg","Enquiry Added");
		}else {
			model.addAttribute("errMsg","Error occured");
		}
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		// get courses for drop down
		
		List<String> courses = enqService.getCourses();
		
		// get enquiry status for drop down
		
		List<String> enqStatuses = enqService.getEnqStatuses();
		
		// create binding class obj
		
		EnquiryForm formObj = new EnquiryForm();	
		
		// set data in model obj
		
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj);
		
		return "add-enquiry";
	}
	
	private void initForm (Model model) {
		
		//get courses for drop down
		List<String> courses = enqService.getCourses();
		
		//get enq status for drop down 
		List<String> enqStatuses = enqService.getEnqStatuses();

		// create binding class obj
		
		EnquiryForm formObj = new EnquiryForm();
		
		// set data in model obj
		
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj);
		
	}
	
	
	@GetMapping("/enquiries")
	public String viewEnquiriesPage( Model model) {
		initForm(model);
		List<StudentEnqEntity> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		return "viewenquiries";
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs (@RequestParam String cname, @RequestParam String status,
								  @RequestParam String mode, Model model) {
		
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setCourseName(cname);
		criteria.setClassMode(mode);
		criteria.setEnqStatus(status);
		
		System.out.println(criteria);
		
		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);
		
		List<StudentEnqEntity> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
		model.addAttribute("enquiries", filteredEnqs);
		
		
		return "filterenquiries";
	}
	
	@GetMapping("/editenquiry")
	public String editEnqPage(Model model) {
		
		EnquiryForm form = new EnquiryForm();
		
		
		return "";
	}
}
