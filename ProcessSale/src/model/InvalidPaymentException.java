/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * This exception is thrown whenever a payment is entered that does not match the
 * cost of the current sale.
 */
public class InvalidPaymentException extends Exception {
    private final int payment;
    private final int price;
    
    public InvalidPaymentException(int payment, int price) {
        super("The payment " + payment + "kr is not enough for the price " + price + "kr.");
        this.payment = payment;
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getPayment() {
        return payment;
    }
    
}
