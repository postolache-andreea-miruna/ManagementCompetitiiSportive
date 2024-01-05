package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.exception.DuplicateClubException;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.repositories.ClubRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ClubServiceImplTest {
    @InjectMocks
    private ClubServiceImpl clubService;

    @Mock
    private ClubRepository clubRepository;

    @Test
    void whenClubAlreadyExists_saveClub_throwsDuplicateClubException() {
        //Arrange
        Club club = new Club();
        club.setNume("BSC");
        club.setNrInregistrare(200);

        when(clubRepository.findByNrInregistrare(club.getNrInregistrare()))
                .thenReturn(Optional.of(club));

        //Act
        DuplicateClubException exception = assertThrows(DuplicateClubException.class,
                () -> clubService.saveClub(club));

        //Assert
        assertEquals("Deja exista un club cu acest numar de inregistrare.", exception.getMessage());
        verify(clubRepository, times(0)).save(club);
    }

    @Test
    void whenClubDoesntExist_saveClub_savesTheClub(){
        //Arrange
        Club club = new Club();
        club.setNume("BSC");
        club.setNrInregistrare(200);

        when(clubRepository.findByNrInregistrare(club.getNrInregistrare()))
                .thenReturn(Optional.empty());

        Club savedClub = new Club();
        savedClub.setNume("BSC");
        savedClub.setNrInregistrare(200);
        savedClub.setId(1);

        when(clubRepository.save(club)).thenReturn(savedClub);

        //Act
        Club result = clubService.saveClub(club);

        //Assert
        assertNotNull(result);
        assertEquals(savedClub.getId(),result.getId());

        assertEquals(savedClub.getNume(),result.getNume());
        assertEquals(club.getNume(),result.getNume());

        assertEquals(savedClub.getNrInregistrare(),result.getNrInregistrare());
        assertEquals(club.getNrInregistrare(),result.getNrInregistrare());

        verify(clubRepository).findByNrInregistrare(club.getNrInregistrare());
        verify(clubRepository).save(club);
    }

    @Test
    void testGetAllCluburi(){
        //Arrange

        Club club1 = new Club();
        club1.setId(1);
        club1.setNume("BSC");
        club1.setNrInregistrare(200);

        Club club2 = new Club();
        club2.setId(2);
        club2.setNume("Star");
        club2.setNrInregistrare(201);

        List<Club> clubList = new ArrayList<>();
        clubList.add(club1);
        clubList.add(club2);

        when(clubRepository.findAll()).thenReturn(clubList);

        //Act
        List<Club> result = clubService.getAllCluburi();

        //Assert
        assertEquals(clubList.size(),result.size());
        assertEquals(clubList,result);

    }

    @Test
    void whenClubDoesntExists_getClubById_throwsClubNotFoundException(){
        //Arrange
        Long nonExistingClubId = 1L;
        when(clubRepository.findById(nonExistingClubId))
                .thenReturn(Optional.empty());
        //Act
        ClubNotFoundException exception = assertThrows(ClubNotFoundException.class,
                () -> clubService.getClubById(nonExistingClubId));

        //Assert
        assertEquals("Clubul cu id " + nonExistingClubId + " nu exista", exception.getMessage());

    }

    @Test
    void whenClubExists_getClubById_returnsTheClub(){
        //Arrange
        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(200);

        when(clubRepository.findById(1L))
                .thenReturn(Optional.of(club));

        //Act
        Optional<Club> result = clubService.getClubById(1L);

        //Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(club.getId(),result.get().getId());
    }
    @Test
    void whenClubExists_updateClub_returnsNewTheClub(){
        //Arrange
        Club clubOld = new Club();
        clubOld.setId(1);
        clubOld.setNume("BSC");
        clubOld.setNrInregistrare(200);

        Long id = 1L;
        String numeClub = "BSC Elite";

        when(clubRepository.findById(id))
                .thenReturn(Optional.of(clubOld));

        Club clubNew = new Club();
        clubNew.setId(1);
        clubNew.setNume(numeClub);
        clubNew.setNrInregistrare(200);

        when(clubRepository.getById(id))
                .thenReturn(clubOld);
        when(clubRepository.save(clubNew)).thenReturn(clubNew);

        //Act
        Club result = clubService.updateClub(id,numeClub);

        //Assert
        assertEquals(clubOld.getId(),result.getId());
        assertEquals(clubOld.getNrInregistrare(),result.getNrInregistrare());
        assertEquals(numeClub,result.getNume());

        verify(clubRepository).findById(id);
        verify(clubRepository).getById(id);
        verify(clubRepository).save(clubNew);
    }

    @Test
    void whenClubDoesntExists_updateClub_throwsClubNotFoundException(){
        //Arrange
        Long id = 1L;
        String numeClub = "BSC Elite";

        when(clubRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act
        ClubNotFoundException exception = assertThrows(ClubNotFoundException.class,
                () -> clubService.updateClub(id,numeClub));

        //Assert
        assertEquals("Clubul cu id " + id + " nu exista", exception.getMessage());

    }

}
