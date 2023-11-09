package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class StockServiceImplTest {

    @Autowired
    private IStockService stockService;

    @Test
    void testAddStock() {
        Stock stock = new Stock("Test Stock", 100, 20);
        stock = stockService.addStock(stock);
        assertNotNull(stock.getIdStock());
    }

    @Test
    void testRetrieveAllStocks() {
        List<Stock> stocks = stockService.retrieveAllStocks();
        assertNotNull(stocks);
    }

    @Test
    void testRetrieveStock() {
        Stock astock = new Stock("Test Stock", 100, 20);
        astock = stockService.addStock(astock);
        Stock stock = stockService.retrieveStock(astock.getIdStock());
        assertNotNull(stock);
    }

    @Test
    void testDeleteStock() {
        Stock stock = new Stock("Test Stock", 100, 20);
        stock = stockService.addStock(stock);
        stockService.deleteStock(stock.getIdStock());
        assertNull(stockService.retrieveStock(stock.getIdStock()));
    }

    @Test
    void testUpdateStock() {
        Stock stock = new Stock("Test Stock", 100, 20);
        stock = stockService.addStock(stock);
        stock.setLibelleStock("Updated Stock");
        stock = stockService.updateStock(stock);
        assertEquals("Updated Stock", stock.getLibelleStock());
    }
}
