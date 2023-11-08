package tn.esprit.rh.achat.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class FactureServiceImpltest {

    @Mock
    FactureRepository factureRepository;

    @InjectMocks
    FactureServiceImpl factureService;

    @InjectMocks
    ReglementServiceImpl reglementService ;

   Facture facture = new Facture();


    @Test
    @Order(1)
    public void testretrieveAllFacture(){

        List<Facture> listFacture = factureService.retrieveAllFactures();

        Assertions.assertEquals(0, listFacture.size());

    }


    @Test
    @Order(3)
    public void testretrieveCategorieProduit(){
        Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(facture));
        Facture facture1 = factureService.retrieveFacture(new Long(1));
        Assertions.assertNotNull(facture1);

    }

    @Test
    @Order(2)
    public void testAddCategorieProduit() {

        Facture fc = new Facture();


        Mockito.when(factureRepository.save(fc)).thenReturn(fc);

        Facture addedCategorieProduit = factureService.addFacture(fc);

        Mockito.verify(factureRepository).save(fc);

        Assertions.assertEquals(fc, addedCategorieProduit);
    }



    @Test
    @Order(4)
    public void testCancelFacture() {
        // Create a Facture ID for testing
        Long factureId = 1L;

        // Create a Facture instance for testing
        Facture fact = new Facture(factureId , 13.1F , 10.4F ,new Date() ,
                new Date() , false , new HashSet<DetailFacture>(),new Fournisseur(),
                new HashSet<Reglement>()
                );


        // Stub the findById method to return the facture
        Mockito.when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        // Call the method to be tested
        factureService.cancelFacture(factureId);

        // Verify that save method was called with the updated facture
        Mockito.verify(factureRepository).save(facture);

        // Verify that updateFacture method was called with the factureId
        Mockito.verify(factureRepository).updateFacture(factureId);
    }


    /*
    @Test
    @Order(5)
    public void testPourcentageRecouvrement() {
        Date startDate = new Date(); // Replace with your desired start date
        Date endDate = new Date();   // Replace with your desired end date

        // Stub the methods of the repositories with test data
        float totalFactures = 100.0f; // Replace with a test value
        Mockito.when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(totalFactures);

        float totalRecouvrement = 80.0f; // Replace with a test value
        Mockito.when(reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(totalRecouvrement);

        // Call the method to be tested
        float expectedPourcentage = (totalRecouvrement / totalFactures) * 100;
        float result = factureService.pourcentageRecouvrement(startDate, endDate);

        // Verify that the expected percentage matches the result
        Assertions.assertEquals(expectedPourcentage, result, 0.01); // Add a delta for float comparison
    }*/

}



