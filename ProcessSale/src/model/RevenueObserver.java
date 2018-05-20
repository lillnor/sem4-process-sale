/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Robert Lillnor
 */
public interface RevenueObserver {
    
    /**
    * Updates the total revenue collected from all sales made during the
    * program's execution.
    * @param newTotal The new total that will be added to the total revenue.
    */  
    void updateRevenue(int newTotal);
}
