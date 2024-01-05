package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.exception.*;
import com.example.managementcompetitii.model.*;
import com.example.managementcompetitii.repositories.AntrenorRepository;
import com.example.managementcompetitii.repositories.ClubRepository;
import com.example.managementcompetitii.repositories.SportivRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class SportivServiceImplTest {
    @InjectMocks
    private SportivServiceImpl sportivService;

    @Mock
    private SportivRepository sportivRepository;
    @Mock
    private AntrenorRepository antrenorRepository;
    @Mock
    private ClubRepository clubRepository;

    @Test
    void whenSportivAlreadyExists_saveSportiv_throwsDuplicateSportivException(){
        //Arrange
        Sportiv sportiv = new Sportiv();
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setAntrenor(new Antrenor(1));

        when(sportivRepository.findByNrLegitimatie(sportiv.getNrLegitimatie()))
                .thenReturn(Optional.of(sportiv));

        //Act
        DuplicateSportivException exception = assertThrows(DuplicateSportivException.class,
                () -> sportivService.saveSportiv(sportiv));

        //Assert
        assertEquals("Exista sportiv care are acest numar de legitimatie", exception.getMessage());
        verify(sportivRepository).findByNrLegitimatie(sportiv.getNrLegitimatie());
        verify(sportivRepository, times(0)).save(sportiv);
    }

    @Test
    void whenSportivDoesntExist_saveSportiv_savesTheSportiv(){
        //Arrange
        Sportiv sportiv = new Sportiv();
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(new Antrenor(1));

        when(sportivRepository.findByNrLegitimatie(sportiv.getNrLegitimatie()))
                .thenReturn(Optional.empty());

        Sportiv savedSportiv = new Sportiv();
        savedSportiv.setNume("Spirida");
        savedSportiv.setPrenume("Maria");
        savedSportiv.setGen("F");
        savedSportiv.setAnNastere(2000);
        savedSportiv.setNrLegitimatie(20);
        savedSportiv.setIndemnizatie(200.5);
        savedSportiv.setAntrenor(new Antrenor(1));
        savedSportiv.setId(2);

        when(sportivRepository.save(sportiv)).thenReturn(savedSportiv);

        //Act
        Sportiv result = sportivService.saveSportiv(sportiv);

        //Assert
        assertNotNull(result);
        assertEquals(savedSportiv.getId(),result.getId());

        assertEquals(savedSportiv.getNume(),result.getNume());
        assertEquals(sportiv.getNume(),result.getNume());

        assertEquals(savedSportiv.getPrenume(),result.getPrenume());
        assertEquals(sportiv.getPrenume(),result.getPrenume());

        assertEquals(savedSportiv.getGen(),result.getGen());
        assertEquals(sportiv.getGen(),result.getGen());

        assertEquals(savedSportiv.getAnNastere(),result.getAnNastere());
        assertEquals(sportiv.getAnNastere(),result.getAnNastere());

        assertEquals(savedSportiv.getNrLegitimatie(),result.getNrLegitimatie());
        assertEquals(sportiv.getNrLegitimatie(),result.getNrLegitimatie());

        assertEquals(savedSportiv.getIndemnizatie(),result.getIndemnizatie());
        assertEquals(sportiv.getIndemnizatie(),result.getIndemnizatie());

        assertEquals(savedSportiv.getAntrenor(),result.getAntrenor());
        assertEquals(sportiv.getAntrenor(),result.getAntrenor());

        verify(sportivRepository).findByNrLegitimatie(sportiv.getNrLegitimatie());
        verify(sportivRepository).save(sportiv);
    }

    @Test
    void whenSportivDoesntExists_updateSportiv_throwsSportivNotFoundException(){
        //Arrange
        Long id = 1L;
        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(new Antrenor(1));

        when(sportivRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act
        SportivNotFoundException exception = assertThrows(SportivNotFoundException.class,
                () -> sportivService.updateSportiv(id,sportiv));

        //Assert
        assertEquals("Sportivul cu id " + id + " nu exista.", exception.getMessage());
        verify(sportivRepository).findById(id);
    }

    @Test
    void whenAntrenorExists_updateAntrenor_returnsNewTheAntrenor(){
        //Arrange
        Long id = 1L;


        Sportiv sportivOld = new Sportiv();
        sportivOld.setId(1);
        sportivOld.setNume("Spirida");
        sportivOld.setPrenume("Maria");
        sportivOld.setGen("F");
        sportivOld.setAnNastere(2000);
        sportivOld.setNrLegitimatie(20);
        sportivOld.setIndemnizatie(200.5);
        sportivOld.setAntrenor(new Antrenor(1));

        when(sportivRepository.findById(id.longValue()))
                .thenReturn(Optional.of(sportivOld));

        Sportiv sportivNew = new Sportiv();
        sportivNew.setId(1);
        sportivNew.setNume("Spiridan");
        sportivNew.setPrenume("Mariana");
        sportivNew.setGen("F");
        sportivNew.setAnNastere(2000);
        sportivNew.setNrLegitimatie(20);
        sportivNew.setIndemnizatie(210.5);
        sportivNew.setAntrenor(new Antrenor(1));

        when(sportivRepository.getById(id))
                .thenReturn(sportivOld);
        when(sportivRepository.save(sportivNew)).thenReturn(sportivNew);

        //Act
        Sportiv result = sportivService.updateSportiv(id,sportivNew);

        //Assert
        assertNotNull(result);

        assertEquals(sportivOld.getId(),result.getId());
        assertEquals(sportivNew.getId(),result.getId());

        assertEquals(sportivNew.getNume(),result.getNume());
        assertEquals(sportivNew.getPrenume(),result.getPrenume());
        assertEquals(sportivNew.getGen(),result.getGen());
        assertEquals(sportivNew.getAnNastere(),result.getAnNastere());
        assertEquals(sportivNew.getNrLegitimatie(),result.getNrLegitimatie());
        assertEquals(sportivNew.getAntrenor(),result.getAntrenor());

        verify(sportivRepository).findById(id);
        verify(sportivRepository).getById(id);
        verify(sportivRepository).save(sportivNew);
    }

    @Test
    void whenClubDoesntExists_getSportiviByIdClub_throwsClubNotFoundException(){
        //Arrange
        Long idClub = 1L;
        when(clubRepository.findById(idClub))
                .thenReturn(Optional.empty());
        //Act
        ClubNotFoundException exception = assertThrows(ClubNotFoundException.class,
                () -> sportivService.getSportiviByIdClub(idClub));

        //Assert
        assertEquals("Clubul cu id " + idClub + " nu exista", exception.getMessage());

    }

    @Test
    void whenClubExists_getSportiviByIdClub_returnsTheSportiviByIdClub(){
        //Arrange
        Long idClub = 1L;
        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Antrenor antrenor2 = new Antrenor();
        antrenor2.setNume("Cona");
        antrenor2.setPrenume("Marcel");
        antrenor2.setAniExperienta(3);
        antrenor2.setSalariu(300.5f);
        antrenor2.setGen("M");
        antrenor2.setClub(new Club(2,"SMB",501));
        antrenor2.setId(2);

        when(clubRepository.findById(idClub))
                .thenReturn(Optional.of(club));

        Sportiv sportiv1 = new Sportiv();
        sportiv1.setId(1);
        sportiv1.setNume("Spirida");
        sportiv1.setPrenume("Maria");
        sportiv1.setGen("F");
        sportiv1.setAnNastere(2000);
        sportiv1.setNrLegitimatie(20);
        sportiv1.setIndemnizatie(200.5);
        sportiv1.setAntrenor(antrenor1);

        Sportiv sportiv2 = new Sportiv();
        sportiv2.setId(2);
        sportiv2.setNume("Sora");
        sportiv2.setPrenume("Maria");
        sportiv2.setGen("F");
        sportiv2.setAnNastere(2001);
        sportiv2.setNrLegitimatie(21);
        sportiv2.setIndemnizatie(200.5);
        sportiv2.setAntrenor(antrenor2);

        Sportiv sportiv3 = new Sportiv();
        sportiv3.setId(3);
        sportiv3.setNume("Saran");
        sportiv3.setPrenume("Marck");
        sportiv3.setGen("M");
        sportiv3.setAnNastere(1989);
        sportiv3.setNrLegitimatie(201);
        sportiv3.setIndemnizatie(400.2);
        sportiv3.setAntrenor(antrenor1);

        List<Sportiv> sportivList = new ArrayList<>();
        sportivList.add(sportiv1);
        sportivList.add(sportiv3);

        when(sportivRepository.findAll()).thenReturn(sportivList);

        //Act
        List<Sportiv> result = sportivService.getSportiviByIdClub(idClub);

        //Assert
        assertNotNull(result);
        assertEquals(club.getId(),result.get(0).getAntrenor().getClub().getId());
        assertEquals(club.getId(),result.get(1).getAntrenor().getClub().getId());
        assertEquals(sportivList,result);

        verify(clubRepository).findById(idClub);
        verify(sportivRepository).findAll();
    }

    @Test
    void whenAntrenorDoesntExists_getSportiviByIdAntrenor_throwsAntrenorNotFoundException(){
        //Arrange
        Long idAntrenor = 1L;
        when(antrenorRepository.findById(idAntrenor.longValue()))
                .thenReturn(Optional.empty());
        //Act
        AntrenorNotFoundException exception = assertThrows(AntrenorNotFoundException.class,
                () -> sportivService.getSportiviByIdAntrenor(idAntrenor));

        //Assert
        assertEquals("Antrenorul cu id " + idAntrenor + " nu exista", exception.getMessage());
    }

    @Test
    void whenAntrenorExists_getSportiviByIdAntrenor_returnsTheSportiviByIdAntrenor(){
        //Arrange
        Long idAntrenor = 1L;

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Antrenor antrenor2 = new Antrenor();
        antrenor2.setNume("Cona");
        antrenor2.setPrenume("Marcel");
        antrenor2.setAniExperienta(3);
        antrenor2.setSalariu(300.5f);
        antrenor2.setGen("M");
        antrenor2.setClub(club);
        antrenor2.setId(2);

        when(antrenorRepository.findById(idAntrenor.longValue()))
                .thenReturn(Optional.of(antrenor1));

        Sportiv sportiv1 = new Sportiv();
        sportiv1.setId(1);
        sportiv1.setNume("Spirida");
        sportiv1.setPrenume("Maria");
        sportiv1.setGen("F");
        sportiv1.setAnNastere(2000);
        sportiv1.setNrLegitimatie(20);
        sportiv1.setIndemnizatie(200.5);
        sportiv1.setAntrenor(antrenor1);

        Sportiv sportiv2 = new Sportiv();
        sportiv2.setId(2);
        sportiv2.setNume("Sora");
        sportiv2.setPrenume("Maria");
        sportiv2.setGen("F");
        sportiv2.setAnNastere(2001);
        sportiv2.setNrLegitimatie(21);
        sportiv2.setIndemnizatie(200.5);
        sportiv2.setAntrenor(antrenor2);

        Sportiv sportiv3 = new Sportiv();
        sportiv3.setId(3);
        sportiv3.setNume("Saran");
        sportiv3.setPrenume("Marck");
        sportiv3.setGen("M");
        sportiv3.setAnNastere(1989);
        sportiv3.setNrLegitimatie(201);
        sportiv3.setIndemnizatie(400.2);
        sportiv3.setAntrenor(antrenor1);

        List<Sportiv> sportivList = new ArrayList<>();
        sportivList.add(sportiv1);
        sportivList.add(sportiv3);

        when(sportivRepository.findAll()).thenReturn(sportivList);

        //Act
        List<Sportiv> result = sportivService.getSportiviByIdAntrenor(idAntrenor);

        //Assert
        assertNotNull(result);
        assertEquals(antrenor1.getId(),result.get(0).getAntrenor().getId());
        assertEquals(antrenor1.getId(),result.get(1).getAntrenor().getId());
        assertEquals(sportivList,result);

        verify(antrenorRepository).findById(idAntrenor);
        verify(sportivRepository).findAll();
    }

}
