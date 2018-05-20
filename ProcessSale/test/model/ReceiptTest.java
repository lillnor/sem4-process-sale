/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import integration.ItemDTO;
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
public class ReceiptTest {
    
    public ReceiptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createReceiptString method, of class Receipt.
     */
    @Test
    public void testCreateReceiptString() throws InvalidPaymentException {
        System.out.println("createReceiptString");
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(20, "TestItem1", 0.12, "id1"));
        sale.addItem(new ItemDTO(40, "TestItem2", 0.12, "id2"));
        sale.addItem(new ItemDTO(20, "TestItem1", 0.12, "id1"));
        sale.calculateTaxes();
        sale.calculateChange(100);
        Receipt instance = new Receipt(sale);
        String expResult = "*KVITTO*\n\nTransaktionstid: " + sale.getSaleTime().toString() + "\n\n2x TestItem1 (20kr)    40kr\n1x TestItem2 (40kr)    40kr\n\nTotal kostnad: 80kr\nTotal moms: 9.6kr\nVäxel: 20kr\n\n\n";
        String result = instance.createReceiptString();
        System.out.println(expResult + result);
        assertEquals(expResult, result);
    }
    
}
