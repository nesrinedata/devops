package tn.esprit.rh.achat.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryproduitMock {

    @Mock
    CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    CategorieProduitServiceImpl categorieProduitService;
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
    public void testretrieveAllCategorieProduits(){

        List<CategorieProduit> lCat = categorieProduitService.retrieveAllCategorieProduits();

        Assertions.assertEquals(0, lCat.size());

    }


    @Test
    @Order(3)
    public void testretrieveCategorieProduit(){
        Mockito.when(categorieProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categorieProduit));
        CategorieProduit categorieProduit1 = categorieProduitService.retrieveCategorieProduit(new Long(1));
        Assertions.assertNotNull(categorieProduit1);

    }

    @Test
    @Order(2)
    public void testAddCategorieProduit() {

        CategorieProduit cp = new CategorieProduit();


        Mockito.when(categorieProduitRepository.save(cp)).thenReturn(cp);

        CategorieProduit addedCategorieProduit = categorieProduitService.addCategorieProduit(cp);

        Mockito.verify(categorieProduitRepository).save(cp);

        Assertions.assertEquals(cp, addedCategorieProduit);
    }



    @Test
    @Order(4)
    public void testDeleteCategorieProduit() {
        Long categoryIdToDelete = 1L;

        categorieProduitService.deleteCategorieProduit(categoryIdToDelete);

        Mockito.verify(categorieProduitRepository).deleteById(categoryIdToDelete);


    }







}
