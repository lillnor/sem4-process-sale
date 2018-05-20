/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.RevenueObserver;

/**
 *
 * @author Robert Lillnor
 */
public class TotalRevenueView implements RevenueObserver {
    private int revenue = 0;
    
    /**
     * Updates the total revenue of all sales with a new {@link Sale}'s
     * running total and prints it.
     * @param newTotal The new running total to be added to the total revenue.
     */
    @Override
    public void updateRevenue(int newTotal) {
        revenue += newTotal;
        printCurrentState();
    }
    
    private void printCurrentState() {
        System.out.println("The current revenue is " + revenue + "kr.");
    }
}
