package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    //find all where isPublishedTrue
    List<Residence> findByIsPublishedTrue();
}
