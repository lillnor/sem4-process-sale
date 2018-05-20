/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.AccountingSystem;
import integration.InventorySystem;
import integration.ItemNotFoundException;
import integration.RegistryCreator;
import integration.Printer;
import model.InvalidPaymentException;
import model.ItemInformation;
import model.TaxInformation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Lillnor
 */
public class ControllerTest {
    
    private Controller instance;
    private final String itemID1 = "LK";
    private final String itemID2 = "MK";
    private final String itemID3 = "SK";
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
        RegistryCreator regCreator    = new RegistryCreator();
        Printer printer               = new Printer();
        AccountingSystem accounting   = new AccountingSystem();
        InventorySystem inventory     = new InventorySystem();
        instance = new Controller(regCreator, printer, accounting, inventory);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of startSale method, of class Controller.
     */
    @Test
    public void testStartSale() {
        System.out.println("startSale");
        instance.startSale();
    }

    /**
     * Test of enterItemID method, of class Controller.
     */
    @Test
    public void testEnterItemID() throws ItemNotFoundException {
        System.out.println("enterItemID");
        instance.startSale();
        ItemInformation expResult = new ItemInformation(20, "stor kaffe", 20);
        ItemInformation result = instance.enterItemID(itemID3);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIndicateAllItemsRegistered() throws ItemNotFoundException {
        System.out.println("enterIndicateAllItemsRegistered");
        instance.startSale();
        TaxInformation expResult = new TaxInformation(135, 16.2);
        System.out.println(instance.enterItemID(itemID1));
        System.out.println(instance.enterItemIDAndQuantity(itemID2, 3));
        System.out.println(instance.enterItemID(itemID3));
        System.out.println(instance.enterItemID(itemID3));
        System.out.println(instance.enterItemIDAndQuantity(itemID1, 2));
        System.out.println(instance.enterItemIDAndQuantity(itemID3, 1));
        TaxInformation result = instance.indicateAllItemsRegistered();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIndicateAllItemsRegistered2() throws ItemNotFoundException {
        System.out.println("enterIndicateAllItemsRegistered2");
        instance.startSale();
        TaxInformation expResult = new TaxInformation(60, 7.2);
        instance.enterItemID(itemID3);
        instance.enterItemID(itemID3);
        instance.enterItemIDAndQuantity(itemID3,1);
        TaxInformation result = instance.indicateAllItemsRegistered();
        assertEquals(expResult, result);
     }

    /**
     * Test of enterPaidCash method, of class Controller.
     */
    @Test
    public void testEnterPaidCash() throws ItemNotFoundException, InvalidPaymentException {
        System.out.println("enterPaidCash");
        int paidAmount                = 80;
        instance.startSale();
        System.out.println(instance.enterItemID("LK"));
        System.out.println(instance.enterItemIDAndQuantity("SK", 3));
        System.out.println(instance.indicateAllItemsRegistered());
        int expResult = 10;
        int result = instance.enterPaidCash(paidAmount);
        assertEquals(expResult, result);
    }
}
