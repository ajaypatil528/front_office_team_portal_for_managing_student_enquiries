package in.ajay.itech.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="AJIT_STUDENT_ENQUIRIES")
@Data
public class StudentEnqEntity {

	@Id
	private Integer userId;
	
	private Integer studentEnqID;
	
	private String studentName;
	
	private Integer studentPhone;
	
	private String studentClassMode;
	
	private String studentCourseName;
	
	private String studentEnqStatus;
	
	private LocalDate createdDate;
	
	private LocalDate updatedDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStudentEnqID() {
		return studentEnqID;
	}

	public void setStudentEnqID(Integer studentEnqID) {
		this.studentEnqID = studentEnqID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(Integer studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentClassMode() {
		return studentClassMode;
	}

	public void setStudentClassMode(String studentClassMode) {
		this.studentClassMode = studentClassMode;
	}

	public String getStudentCourseName() {
		return studentCourseName;
	}

	public void setStudentCourseName(String studentCourseName) {
		this.studentCourseName = studentCourseName;
	}

	public String getStudentEnqStatus() {
		return studentEnqStatus;
	}

	public void setStudentEnqStatus(String studentEnqStatus) {
		this.studentEnqStatus = studentEnqStatus;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
