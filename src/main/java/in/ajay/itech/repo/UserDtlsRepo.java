package in.ajay.itech.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.itech.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	public UserDtlsEntity findByEmail(String email);
}
