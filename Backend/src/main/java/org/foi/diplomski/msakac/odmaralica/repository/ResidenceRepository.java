package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    //find all where isPublishedTrue
    List<Residence> findByIsPublishedTrue();
}
