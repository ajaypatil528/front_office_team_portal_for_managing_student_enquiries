package in.ajay.itech.service;

import java.util.List;

import in.ajay.itech.binding.DashboardResponse;
import in.ajay.itech.binding.EnquiryForm;
import in.ajay.itech.binding.EnquirySearchCriteria;

public interface EnquiryService {
	
	public List<String> getCourseNames();
	
	public List<String> getEnqStatus();

	public DashboardResponse getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryForm form);
	
	public List<EnquiryForm> getEnquries(Integer userId,
										EnquirySearchCriteria criteria);
	
	public EnquiryForm getEnquiry(Integer enqId);
}
