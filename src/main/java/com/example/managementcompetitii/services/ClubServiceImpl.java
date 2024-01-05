package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.exception.DuplicateClubException;
import com.example.managementcompetitii.exception.DuplicateTipException;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClubServiceImpl implements ClubService{
    ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
//-	GetAllClub -DONE
//-	GetClubById -DONE
//-	AddNewClub - DONE
//-	UpdateClub
    public List<Club> getAllCluburi()//get all
    {
        return clubRepository.findAll();
    }

//    public Optional<Club> getClubById(Long id)//get by id
//    {
//        return clubRepository.findById(id);
//    }
    public Optional<Club> getClubById(Long id)//get by id
    {
        Optional<Club> club = clubRepository.findById(id);
        if (club.isPresent()) {
            return clubRepository.findById(id);
        }
        else {
            throw new ClubNotFoundException(id);
        }
    }

    public Club saveClub(Club club) //create
    {
        Optional<Club> clubExistent = clubRepository.findByNrInregistrare(club.getNrInregistrare());
        clubExistent.ifPresent(c -> {
            throw new DuplicateClubException();
        });
        return clubRepository.save(club);
    }

//    public Club updateClub(Long id, String numeClub) throws Exception
//    {
//        Club club = clubRepository.getById(id);
//        if(club != null){
//            club.setNume(numeClub);
//            return clubRepository.save(club);
//        }else
//        {
//            throw new ClubNotFoundException(id);
//        }
//    }
    public Club updateClub(Long id, String numeClub)
    {
        Optional<Club> club = clubRepository.findById(id);
        if(club.isPresent()){
            Club clubul = clubRepository.getById(id);
            clubul.setNume(numeClub);
            return clubRepository.save(clubul);
        }
        else
        {
            throw new ClubNotFoundException(id);
        }
    }
}
