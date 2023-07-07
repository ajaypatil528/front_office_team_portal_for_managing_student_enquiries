package in.ajay.itech.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.itech.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

}
