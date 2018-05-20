/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

/**
 * This exception is thrown whenever an ID that does not exist in the item
 * registry is entered by the user.
 */
public class ItemNotFoundException extends Exception {
    
    private final String ID;
    
    public ItemNotFoundException(String itemID) {
        super("The ID " + itemID + " could not be found in the database.");
        ID = itemID;
    }
    
    public String getID() {
        return ID;
    }
}
