package in.ajay.itech.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.itech.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{

}
