package in.ajay.itech.service;

import java.util.List;

import in.ajay.itech.binding.DashboardResponse;
import in.ajay.itech.binding.EnquiryForm;
import in.ajay.itech.binding.EnquirySearchCriteria;
import in.ajay.itech.entity.StudentEnqEntity;

public interface EnquiryService {
	
	
	public DashboardResponse getDashboardData(Integer userId);
	
	public List<String> getCourses();
	
	public List<String> getEnqStatuses();
	
	public boolean saveEnquiry(EnquiryForm form);
	
	public List<StudentEnqEntity> getEnquiries();
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId);
	
	//public String upsertEnquiry(EnquiryForm form);
	
	//public List<EnquiryForm> getEnquries(Integer userId,
	//									EnquirySearchCriteria criteria);
	
	//public EnquiryForm getEnquiry(Integer enqId);
}
