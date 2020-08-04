package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BackLog;

@Repository
public interface BackLogRepo extends CrudRepository<BackLog,Long>{
	
	BackLog findByProjectIdentifier(String projectIdentifier);

}
