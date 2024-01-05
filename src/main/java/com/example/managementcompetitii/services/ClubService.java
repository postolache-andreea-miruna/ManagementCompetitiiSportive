package com.example.managementcompetitii.services;

import com.example.managementcompetitii.model.Club;

import java.util.List;
import java.util.Optional;

public interface ClubService {
    List<Club> getAllCluburi();
    Optional<Club> getClubById(Long id);
    Club saveClub(Club club);
    Club updateClub(Long id, String numeClub);
}
