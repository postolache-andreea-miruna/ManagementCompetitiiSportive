package com.example.managementcompetitii.services;
import com.example.managementcompetitii.exception.AntrenorNotFoundException;
import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.repositories.AntrenorRepository;
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
public class AntrenorServiceImplTest {
    @InjectMocks
    private AntrenorServiceImpl antrenorService;

    @Mock
    private AntrenorRepository antrenorRepository;
    @Mock
    private ClubRepository clubRepository;

    @Test
    void testSaveAntrenor(){
        //Arrange
        Antrenor antrenor = new Antrenor();
        antrenor.setNume("Mili");
        antrenor.setPrenume("Aurelia");
        antrenor.setAniExperienta(2);
        antrenor.setSalariu(200.5f);
        antrenor.setGen("F");
        antrenor.setClub(new Club(1,"Dona",500));

        Antrenor savedAntrenor = new Antrenor();
        savedAntrenor.setNume("Mili");
        savedAntrenor.setPrenume("Aurelia");
        savedAntrenor.setAniExperienta(2);
        savedAntrenor.setSalariu(200.5f);
        savedAntrenor.setGen("F");
        savedAntrenor.setClub(new Club(1,"Dona",500));
        savedAntrenor.setId(1);

        when(antrenorRepository.save(antrenor)).thenReturn(savedAntrenor);

        //Act
        Antrenor result = antrenorService.saveAntrenor(antrenor);

        //Assert
        assertNotNull(result);
        assertEquals(savedAntrenor.getId(), result.getId());

        assertEquals(savedAntrenor.getNume(),result.getNume());
        assertEquals(antrenor.getNume(),result.getNume());

        assertEquals(savedAntrenor.getPrenume(),result.getPrenume());
        assertEquals(antrenor.getPrenume(),result.getPrenume());

        assertEquals(savedAntrenor.getAniExperienta(), result.getAniExperienta());
        assertEquals(antrenor.getAniExperienta(), result.getAniExperienta());

        assertEquals(savedAntrenor.getSalariu(),result.getSalariu());
        assertEquals(antrenor.getSalariu(),result.getSalariu());

        assertEquals(savedAntrenor.getGen(),result.getGen());
        assertEquals(antrenor.getGen(),result.getGen());

        assertEquals(savedAntrenor.getClub(),result.getClub());
        assertEquals(antrenor.getClub(),result.getClub());

        verify(antrenorRepository).save(antrenor);
    }

    @Test
    void whenAntrenorExists_updateAntrenor_returnsNewTheAntrenor(){
        //Arrange
        Long id = 1L;
        Antrenor antrenorOld = new Antrenor();
        antrenorOld.setId(id);
        antrenorOld.setNume("Mili");
        antrenorOld.setPrenume("Aurelia");
        antrenorOld.setAniExperienta(2);
        antrenorOld.setSalariu(200.5f);
        antrenorOld.setGen("F");
        antrenorOld.setClub(new Club(1,"Dona",500));

        when(antrenorRepository.findById(id.longValue()))
                .thenReturn(Optional.of(antrenorOld));

        Antrenor antrenorNew = new Antrenor();
        antrenorNew.setId(id);
        antrenorNew.setNume("Miliena");
        antrenorNew.setPrenume("Aurelia");
        antrenorNew.setAniExperienta(3);
        antrenorNew.setSalariu(200.5f);
        antrenorNew.setGen("F");
        antrenorNew.setClub(new Club(1,"Dona",500));

        when(antrenorRepository.getById(id))
                .thenReturn(antrenorOld);
        when(antrenorRepository.save(antrenorNew)).thenReturn(antrenorNew);

        //Act
        Antrenor result = antrenorService.updateAntrenor(id,antrenorNew);

        //Assert
        assertNotNull(result);
        assertEquals(antrenorNew.getId(),result.getId());
        assertEquals(antrenorNew.getNume(),result.getNume());
        assertEquals(antrenorNew.getPrenume(),result.getPrenume());
        assertEquals(antrenorNew.getAniExperienta(),result.getAniExperienta());
        assertEquals(antrenorNew.getSalariu(),result.getSalariu());
        assertEquals(antrenorNew.getGen(),result.getGen());
        assertEquals(antrenorNew.getClub(),result.getClub());

        verify(antrenorRepository).findById(id);
        verify(antrenorRepository).getById(id);
        verify(antrenorRepository).save(antrenorNew);
    }


    @Test
    void whenAntrenorDoesntExists_updateAntrenor_throwsAntrenorNotFoundException(){
        //Arrange
        Long id = 1L;
        Antrenor antrenor = new Antrenor();
        antrenor.setId(id);
        antrenor.setNume("Mili");
        antrenor.setPrenume("Aurelia");
        antrenor.setAniExperienta(2);
        antrenor.setSalariu(200.5f);
        antrenor.setGen("F");
        antrenor.setClub(new Club(1));

        when(antrenorRepository.findById(id.longValue()))
                .thenReturn(Optional.empty());

        //Act
        AntrenorNotFoundException exception = assertThrows(AntrenorNotFoundException.class,
                () -> antrenorService.updateAntrenor(id,antrenor));

        //Assert
        assertEquals("Antrenorul cu id " + id + " nu exista", exception.getMessage());
    }

    @Test
    void testGetAntrenoriAscNume(){
        //Arrange
        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(new Club(1,"Dona",500));

        Antrenor antrenor2 = new Antrenor();
        antrenor2.setNume("Cona");
        antrenor2.setPrenume("Simona");
        antrenor2.setAniExperienta(3);
        antrenor2.setSalariu(300.5f);
        antrenor2.setGen("F");
        antrenor2.setClub(new Club(2,"SMB",501));
        antrenor2.setId(2);

        List<Antrenor> antrenoriList = new ArrayList<>();
        antrenoriList.add(antrenor2);
        antrenoriList.add(antrenor1);

        when(antrenorRepository.findAllByOrderByNumeAsc()).thenReturn(antrenoriList);

        //Act
        List<Antrenor> result = antrenorService.getAntrenoriAscNume();

        //Assert
        assertEquals(antrenoriList.size(),result.size());
        assertEquals(antrenoriList,result);
        assertEquals(antrenor2,result.get(0));
        assertEquals(antrenor1,result.get(1));
    }

    @Test
    void testGetAntrenoriDescNume(){
        //Arrange
        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(new Club(1,"Dona",500));

        Antrenor antrenor2 = new Antrenor();
        antrenor2.setNume("Cona");
        antrenor2.setPrenume("Simona");
        antrenor2.setAniExperienta(3);
        antrenor2.setSalariu(300.5f);
        antrenor2.setGen("F");
        antrenor2.setClub(new Club(2,"SMB",501));
        antrenor2.setId(2);

        List<Antrenor> antrenoriList = new ArrayList<>();
        antrenoriList.add(antrenor1);
        antrenoriList.add(antrenor2);

        when(antrenorRepository.findAllByOrderByNumeDesc()).thenReturn(antrenoriList);

        //Act
        List<Antrenor> result = antrenorService.getAntrenoriDescNume();

        //Assert
        assertEquals(antrenoriList.size(),result.size());
        assertEquals(antrenoriList,result);
        assertEquals(antrenor1,result.get(0));
        assertEquals(antrenor2,result.get(1));

    }

    @Test
    void testGetAntrenoriFilterGenExperienta(){
        //Arrange
        String gen = "M";
        Integer aniExperienta = 3;

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(new Club(1,"Dona",500));

        Antrenor antrenor2 = new Antrenor();
        antrenor2.setNume("Cona");
        antrenor2.setPrenume("Marcel");
        antrenor2.setAniExperienta(3);
        antrenor2.setSalariu(300.5f);
        antrenor2.setGen("M");
        antrenor2.setClub(new Club(2,"SMB",501));
        antrenor2.setId(2);

        Antrenor antrenor3 = new Antrenor();
        antrenor3.setNume("Ion");
        antrenor3.setPrenume("Ion");
        antrenor3.setAniExperienta(3);
        antrenor3.setSalariu(320.5f);
        antrenor3.setGen("M");
        antrenor3.setClub(new Club(1,"Dona",500));
        antrenor3.setId(3);

        List<Antrenor> antrenorList = new ArrayList<>();
        antrenorList.add(antrenor2);
        antrenorList.add(antrenor3);

        when(antrenorRepository.findByAniExperientaAndGen(aniExperienta,gen)).thenReturn(antrenorList);

        //Act
        List<Antrenor> result = antrenorService.getAntrenoriFilterGenExperienta(gen,aniExperienta);

        //Assert
        assertEquals(2,result.size());
        assertEquals(antrenorList.size(),result.size());
        assertEquals(antrenorList,result);

        verify(antrenorRepository).findByAniExperientaAndGen(aniExperienta,gen);
    }
    @Test
    void whenClubDoesntExists_getAntrenoriByIdClub_throwsClubNotFoundException(){
        //Arrange
        Long idClub = 1L;
        when(clubRepository.findById(idClub.longValue()))
                .thenReturn(Optional.empty());
        //Act
        ClubNotFoundException exception = assertThrows(ClubNotFoundException.class,
                () -> antrenorService.getAntrenoriByIdClub(idClub));

        //Assert
        assertEquals("Clubul cu id " + idClub + " nu exista", exception.getMessage());

    }

    @Test
    void whenClubExists_getAntrenoriByIdClub_returnsTheAntrenoriByIdClub(){
        //Arrange
        Long idClub = 1L;
        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        when(clubRepository.findById(idClub))
                .thenReturn(Optional.of(club));

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

        Antrenor antrenor3 = new Antrenor();
        antrenor3.setNume("Ion");
        antrenor3.setPrenume("Ion");
        antrenor3.setAniExperienta(3);
        antrenor3.setSalariu(320.5f);
        antrenor3.setGen("M");
        antrenor3.setClub(club);
        antrenor3.setId(3);

        List<Antrenor> antrenorList = new ArrayList<>();
        antrenorList.add(antrenor1);
        antrenorList.add(antrenor3);

        when(antrenorRepository.findAll()).thenReturn(antrenorList);

        //Act
        List<Antrenor> result = antrenorService.getAntrenoriByIdClub(idClub);

        //Assert
        assertNotNull(result);
        assertEquals(club.getId(),result.get(0).getClub().getId());
        assertEquals(club.getId(),result.get(1).getClub().getId());

        verify(clubRepository).findById(idClub.longValue());
        verify(antrenorRepository).findAll();
    }
}
