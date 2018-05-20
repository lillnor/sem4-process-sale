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
public class TaxInformation {
        private final int total;
        private final double taxes;
        
        public TaxInformation(int total, double taxes) {
            this.total = total;
            this.taxes = taxes;
        }
        
        @Override
        public boolean equals(Object other){
            if (other == null || !(other instanceof TaxInformation)) {
                return false;
            }
            TaxInformation otherInfo = (TaxInformation) other;
            return (this.total == otherInfo.total && (Math.abs(this.taxes - otherInfo.taxes) < 0.00001));
        }
        
        @Override
	public String toString() {
            return "Total: " + total + "kr, taxes: " + taxes + "kr";
	}
}
