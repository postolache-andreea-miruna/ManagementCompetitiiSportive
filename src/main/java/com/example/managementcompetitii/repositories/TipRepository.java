package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipRepository extends JpaRepository<Tip,Long> {
    Tip getByNume(String numeTip);

    Optional<Tip> findByNume(String nume);
}
