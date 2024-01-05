package com.example.managementcompetitii.repositories;

import com.example.managementcompetitii.model.Participa;
import com.example.managementcompetitii.model.ParticipaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipaRepository extends JpaRepository<Participa, ParticipaId> {

}
