package in.ajay.itech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ajay.itech.binding.DashboardResponse;
import in.ajay.itech.binding.EnquiryForm;
import in.ajay.itech.binding.EnquirySearchCriteria;
import in.ajay.itech.constants.AppConstants;
import in.ajay.itech.entity.CourseEntity;
import in.ajay.itech.entity.EnqStatusEntity;
import in.ajay.itech.entity.StudentEnqEntity;
import in.ajay.itech.entity.UserDtlsEntity;
import in.ajay.itech.repo.CourseRepo;
import in.ajay.itech.repo.EnqStatusRepo;
import in.ajay.itech.repo.StudentEnqRepo;
import in.ajay.itech.repo.UserDtlsRepo;

@Service
public class EnquireyServiceImpl implements EnquiryService {
	
	DashboardResponse response = new DashboardResponse();

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Autowired
	private HttpSession session;
	
		@Override
	public DashboardResponse getDashboardData(Integer userId) {
		// TODO Auto-generated method stub
		
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		
		if(findById.isPresent()) {
			UserDtlsEntity userEntity = findById.get();
			
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
			
			Integer totalCnt = enquiries.size();
			
			Integer enrolledCnt = enquiries.stream().
							filter(e -> e.getEnqStatus().equals("Enrolled")).
							collect(Collectors.toList()).size();
			
			Integer lostCnt = enquiries.stream().
							filter(e -> e.getEnqStatus().equals("Lost")).
							collect(Collectors.toList()).size();
			
			response.setTotalEnquriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
			
		}
		return response;
	}

	/*
	 * @Override public String upsertEnquiry(EnquiryForm form) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<EnquiryForm> getEnquries(Integer userId,
	 * EnquirySearchCriteria criteria) { // TODO Auto-generated method stub return
	 * null; }
	 */


	@Override
	public List<String> getCourses() {

		List<CourseEntity> findAll = courseRepo.findAll();
		
		List<String> names = new ArrayList();
		
		for(CourseEntity entity : findAll) {
			names.add(entity.getCourseName());
		}
		return names;
	}

	@Override
	public List<String> getEnqStatuses() {
		
		List<EnqStatusEntity> findAll = statusRepo.findAll();
		List<String> statusList = new ArrayList<>();
		
		for(EnqStatusEntity entity : findAll) {
			statusList.add(entity.getStatusName());
			
		}
		return statusList;
	}

	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		
		// TODO Auto-generated method stub
		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);
		
		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);
		UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		
		enqRepo.save(enqEntity);
		
		
		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {

		Integer userId = (Integer) session.getAttribute(AppConstants.STR_USER_ID);
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}
		return null;
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {

		
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
		
			//filter logic (Java 8 feature "stream()")
			
			if(null != criteria.getCourseName() && !"".equals(criteria.getCourseName())) {
				
			enquiries =   enquiries.stream()
						 .filter(e -> e.getCourseName()
					     .equals(criteria.getCourseName()))
						 .collect(Collectors.toList());
			}
			
			if(null != criteria.getEnqStatus() && !"".equals(criteria.getEnqStatus())) {
				
				enquiries = enquiries.stream()
							.filter(e -> e.getEnqStatus()
							.equals(criteria.getEnqStatus()))
							.collect(Collectors.toList());
			}
			
			if(null!= criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
				
				enquiries = enquiries.stream()
							.filter(e -> e.getClassMode()
							.equals(criteria.getClassMode()))
							.collect(Collectors.toList());
			}
			return enquiries;
		}
		return null;
	}

}
