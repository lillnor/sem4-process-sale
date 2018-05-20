package model;

import java.time.LocalDateTime;
import integration.ItemDTO;
import integration.Printer;
import java.util.ArrayList;
import java.util.List;

/**
 * Collects all information regarding a particular sale.
 */
public class Sale {
    private final LocalDateTime saleTime;
    private final List<ItemDTO> registeredItems = new ArrayList<>();
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();
    private int runningTotal = 0;
    private int changeAmount = 0;
    private double totalTaxes;

    
    /**
     * Creates a new instance, and records the time it was created.
     * This will be the time recorded on the receipt.
     */
    public Sale() {
        saleTime = LocalDateTime.now();
    }
    
    /**
     * Adds a new {@link RevenueObserver} to the current sale.
     * @param revenueObserver The RevenueObserver to be added.
     */
    public void addRevenueObserver(RevenueObserver revenueObserver) {
        revenueObservers.add(revenueObserver);
    }
    
    /**
     * Notifies all {@link RevenueObserver}s added to the current sale with the new
     * running total of the current sale.
     */
    public void notifyObservers() {
        for (RevenueObserver revenueObserver : revenueObservers) {
            revenueObserver.updateRevenue(runningTotal);
        }
    }
    
    /**
     * Checks if an item exists in <code>registeredItems</code>.
     * @param itemToCheck the item that will be checked.
     * @return whether or not an item in the current sale has the same ID as
     * <code>itemToCheck</code>.
     */
    private boolean itemExists(ItemDTO itemToCheck){
        for (ItemDTO currentItem : registeredItems)
            if (itemToCheck.equals(currentItem))
                return true;
        return false;
    }
    
    /**
     * Gets the item in the current sale with the same ID as <code>itemToFind</code>.
     * @param itemToFind the item that is to be found.
     * @return the item that has the same id as <code>itemToFind</code>.
     */
    private ItemDTO findItem(ItemDTO itemToFind) {
        for (ItemDTO currentItem : registeredItems)
            if (itemToFind.equals(currentItem))
                return currentItem;
        return null;
    }
    
    /**
     * Updates the quantity of an item in the current sale.
     * @param registeredItem the item to update the quantity of.
     * @param quantity the new quantity of the item.
     */
    private void updateQuantity(ItemDTO registeredItem, int quantity) {
         registeredItems.set(registeredItems.indexOf(findItem(registeredItem)),
                new ItemDTO(registeredItem,
                        findItem(registeredItem).getQuantity() + quantity));
    }
    
    /**
     * Updates the list that holds the registered items of the current sale and
     * adds a new item to it. Also creates an information object about the newly
     * added item and the total of the sale.
     * @param registeredItem the item to be added to the current sale
     * @return the information of the latest registered item's price and
     * description, and the current running total of the currently ongoing sale.
     */
    public ItemInformation addItem(ItemDTO registeredItem) {
        if (!itemExists(registeredItem)){
            runningTotal += registeredItem.getPrice();
            registeredItems.add(registeredItem);
        }
        else {
            runningTotal += registeredItem.getPrice();
            updateQuantity(registeredItem, 1);
        }
    	return new ItemInformation(registeredItem.getPrice(),
                registeredItem.getDescription(), runningTotal);
    }
    
    /**
     * Updates the list that holds the registered items of the current sale and
     * adds several of a new item to it. Also creates an information object
     * about the newly added items and the total of the sale.
     * @param registeredItem the item to be added to the current sale
     * @param quantity the quantity of items to be added to the current sale
     * @return the information of the latest registered item's price and
     * description, and the current running total of the currently ongoing sale
     */
    public ItemInformation addItems(ItemDTO registeredItem, int quantity) {
        if (!itemExists(registeredItem)){
            runningTotal += registeredItem.getPrice() * quantity;
            registeredItems.add(new ItemDTO(registeredItem, quantity));
        }
        else {
            runningTotal += registeredItem.getPrice() * quantity;
            updateQuantity(registeredItem, quantity);
        }
    	return new ItemInformation(registeredItem.getPrice(),
                registeredItem.getDescription(), runningTotal);
    }
    
    /**
     * Calculates the total taxes of the current sale based on the items that
     * are registered.
     * @return the total taxes.
     */
    public TaxInformation calculateTaxes() {
        totalTaxes = 0;
        for (ItemDTO currentItem : registeredItems) {
            totalTaxes += currentItem.getPrice() * currentItem.getTax() * currentItem.getQuantity();
        }
        return new TaxInformation(runningTotal, totalTaxes);
    }
    
    /**
     * Calculates the change that is to be given to the customer upon the sale's
     * completion. It can become a negative number, I will change this later.
     * @param paidAmount the amount the customer is paying with.
     * @return the change amount.
     */
    public int calculateChange(int paidAmount) throws InvalidPaymentException {
        if (paidAmount - runningTotal < 0) {
            throw new InvalidPaymentException(paidAmount, runningTotal);
        }
        changeAmount = paidAmount - runningTotal;
        return changeAmount;
    }
    
    /**
     * Prints a receipt containing information about the sale.
     * @param printer the printer that is going to print the receipt.
     */
    public void printReceipt (Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
    
    public List<ItemDTO> getItems() {
        return registeredItems;
    }
    
    /**
     * @return the running total of the current sale.
     */
    public int getRunningTotal() {
        return runningTotal;
    }
    
    /**
     * @return the change of the current sale.
     */
    public int getChange() {
        return changeAmount;
    }
    
    /**
     * @return the total taxes of the current sale.
     */
    public double getTotalTaxes(){
        return totalTaxes;
    }    
    
    /**
     * @return the time that the sale started.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }
}
