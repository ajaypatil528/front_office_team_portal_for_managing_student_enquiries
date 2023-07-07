package in.ajay.itech.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="AJIT_USER_DTLS")
@Data
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String name;
	
	private String email;
	
	private Long phno;
	
	private String pwd;
	
	private String accStatus;
	
	
	
	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =
	  FetchType.EAGER)
	  private List<StudentEnqEntity> enquiries;
	 
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	
	
	  public List<StudentEnqEntity> getEnquiries() { 
		  return enquiries;
	 }
	  
	  public void setEnquiries(List<StudentEnqEntity> enquiries) { 
		  this.enquiries = enquiries;
	 }
	 
	 
	
}
