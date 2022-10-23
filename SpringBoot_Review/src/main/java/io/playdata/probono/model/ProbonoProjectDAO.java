package io.playdata.probono.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.playdata.probono.model.entity.ProbonoProject;

@Repository
public interface ProbonoProjectDAO extends JpaRepository<ProbonoProject, Integer>{

}
