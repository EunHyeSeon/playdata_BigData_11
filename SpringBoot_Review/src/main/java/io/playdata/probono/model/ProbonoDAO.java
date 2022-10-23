package io.playdata.probono.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.playdata.probono.model.entity.Probono;

@Repository
public interface ProbonoDAO extends JpaRepository<Probono, String> {

}
