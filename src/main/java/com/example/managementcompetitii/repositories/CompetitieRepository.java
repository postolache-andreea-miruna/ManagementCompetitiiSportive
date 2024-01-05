package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Competitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitieRepository extends JpaRepository<Competitie,Long> {
    List<Competitie> findAllByOrderByDataStartDesc();

    List<Competitie> findByTipId(Long idTip);

    Optional<Competitie> findByNume(String nume);


}
