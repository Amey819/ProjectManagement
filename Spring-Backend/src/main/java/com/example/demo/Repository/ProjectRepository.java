package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Project;

// Repository is the middleware between api calls and the database
//Extends CrudRepo to get allt eh basic functionalities on the db
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

	// gets all the projects from the db
	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);
	
	Project findByProjectIdentifier(String projectIdentifier);
	
	Iterable<Project> findAll();
	

	
}
