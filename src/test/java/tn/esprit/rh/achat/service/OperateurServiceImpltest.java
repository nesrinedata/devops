package tn.esprit.rh.achat.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImpltest {
    @Mock
    OperateurRepository operateurRepository;

    @InjectMocks
    OperateurServiceImpl operateurService;
    Long id1 = new Long(1);
    Long id2 = new Long(2);
    Long id3 = new Long(3);
    CategorieProduit categorieProduit = new CategorieProduit( id1 , "9UYL"
            , "Blogging Platform" , new HashSet<Produit>());
    List<CategorieProduit> listCategoryProduit = new ArrayList<CategorieProduit>(){
        {
            add(categorieProduit);
          /*  add( new CategorieProduit( id2 , "76FF"
                    , "Restaurant Menu" , new HashSet<Produit>()));
            add( new CategorieProduit( id3 , "98Y7"
                    , "E-commerce Website" , new HashSet<Produit>()));*/
        }
    };


    @Test
    @Order(1)
    public void testretrieveAllOperateur(){

        List<Operateur> listoperateur = operateurService.retrieveAllOperateurs();

        Assertions.assertEquals(0, listoperateur.size());

    }


    @Test
    @Order(3)
    public void testretrieveOperateur(){
        //Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categorieProduit));
        Operateur categorieProduit1 = operateurService.retrieveOperateur(new Long(1));
        Assertions.assertNotNull(categorieProduit1);

    }

    @Test
    @Order(2)
    public void testAddOperateur() {

        Operateur operateurr = new Operateur();


        Mockito.when(operateurRepository.save(operateurr)).thenReturn(operateurr);

        Operateur addedCategorieProduit = operateurService.addOperateur(operateurr);

        Mockito.verify(operateurRepository).save(operateurr);

        Assertions.assertEquals(operateurr, addedCategorieProduit);
    }



    @Test
    @Order(5)
    public void testDeleteCategorieProduit() {
        Long opID = 1L;

        operateurService.deleteOperateur(opID);

        Mockito.verify(operateurRepository).deleteById(opID);


    }

    @Test
    @Order(4)
    public void testUpdateOperateur() {
        // Create a sample Operateur object
        Operateur operateur = new Operateur(1L ,"abed","molka","molka123",new HashSet<Facture>() );


        // Mock the operateurRepository.save method
        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);

        // Call the method you want to test
        Operateur updatedOperateur = operateurService.updateOperateur(operateur);

        // Verify that the save method was called with the correct Operateur object
        Mockito.verify(operateurRepository).save(operateur);

        // Verify that the returned Operateur object is the same as the input
        Assertions.assertEquals(operateur, updatedOperateur);
    }



}
