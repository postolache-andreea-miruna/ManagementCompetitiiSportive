package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
        Optional<Club> findByNumeAndNrInregistrare(String nume, Integer nrInregistrare);

    Optional<Club> findByNrInregistrare(Integer nrInregistrare);


}
