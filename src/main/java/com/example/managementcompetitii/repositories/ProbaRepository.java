package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Proba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProbaRepository extends JpaRepository<Proba,Long> {
    Optional<Proba> findByNume(String nume);
}
