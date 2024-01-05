package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Sportiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportivRepository extends JpaRepository<Sportiv,Long> {
    Optional<Sportiv> findByNrLegitimatie(Integer nrLegitimatie);


}
