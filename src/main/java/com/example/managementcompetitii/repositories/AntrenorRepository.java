package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Antrenor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntrenorRepository extends JpaRepository<Antrenor,Long> {
    List<Antrenor> findAllByOrderByNumeAsc();

    List<Antrenor> findAllByOrderByNumeDesc();

    List<Antrenor> findByAniExperienta(Integer aniExperienta);


    List<Antrenor> findByAniExperientaAndGen(Integer aniExperienta, String gen);
}
