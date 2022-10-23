package io.playdata.probono.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.playdata.probono.model.entity.Activist;

@Repository
public interface ActivistDAO extends JpaRepository<Activist, String>{  
	
	@Modifying
	@Query("update Activist a set a.major=:major where a.id=:id")
	int updateActivistByIdMajor(@Param("id") String id, @Param("major") String major);

}
