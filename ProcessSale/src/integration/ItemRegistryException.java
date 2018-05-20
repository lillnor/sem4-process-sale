/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

/**
 * This exception is thrown whenever there is a database failure concerning a
 * specific Item ID.
 */
public class ItemRegistryException extends NullPointerException {
    public ItemRegistryException(String ID) {
        super("Database failure. Couldn't access ID: " + ID);
    }
}
