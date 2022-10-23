package io.playdata.probono.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.playdata.probono.model.entity.Recipient;

@Repository
public interface RecipientDAO extends JpaRepository<Recipient, String> {
	
	@Modifying
	@Query("update Recipient r set r.receiveHopeContent=:receiveHopeContent where r.id=:id")
	int updateRecipientByIdReceiveHopeContents(@Param("id") String id, @Param("receiveHopeContent") String receiveHopeContent);

}
