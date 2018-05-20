/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import model.InvalidPaymentException;
import model.Receipt;
import model.Sale;
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
public class PrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public PrinterTest() {
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of printReceipt method, of class Printer.
     */
    @Test
    public void testPrintReceipt() throws InvalidPaymentException {
        Sale sale = new Sale();
        sale.addItem(new ItemDTO(20, "TestItem1", 0.12, "id1"));
        sale.addItem(new ItemDTO(40, "TestItem2", 0.12, "id2"));
        sale.addItem(new ItemDTO(20, "TestItem1", 0.12, "id1"));
        sale.calculateTaxes();
        sale.calculateChange(100);
        Receipt receipt = new Receipt(sale);
        Printer instance = new Printer();
        String expResult = "*KVITTO*\n\nTransaktionstid: " + sale.getSaleTime().toString() + "\n\n2x TestItem1 (20kr)    40kr\n1x TestItem2 (40kr)    40kr\n\nTotal kostnad: 80kr\nTotal moms: 9.6kr\nVäxel: 20kr\n\n\n";
        instance.printReceipt(receipt);
        assertEquals(expResult, outContent.toString());
    }
    
}
