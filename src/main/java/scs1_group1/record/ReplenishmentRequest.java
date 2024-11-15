package scs1_group1.record;

/**
 * Represents a replenishment request for a specific medicine.
 * 
 * This class provides information about a request for replenishment of a medicine, including
 * the medicine name, quantity requested, and the current status of the request.
 */
public class ReplenishmentRequest extends Record {
    private String medicineName;
    private int quantity;
    private String status ;

    /**
     * Constructs a new ReplenishmentRequest instance.
     * 
     * @param medicineName The name of the medicine for which replenishment is requested.
     * @param quantity The quantity of the medicine to be replenished.
     * @param status The current status of the replenishment request (e.g., "pending", "approved").
     */
    public ReplenishmentRequest(String medicineName, int quantity , String status) {
        super();
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.status = status;
    }


    /**
     * Returns a string representation of the replenishment request.
     * This implementation returns the medicine name and quantity in parentheses.
     * 
     * @return The string representation of the replenishment request.
     */
    @Override
    public String toString() {
        return medicineName + " (" + quantity + ")";
    }


    /**
     * Gets the name of the medicine for which replenishment is requested.
     * 
     * @return The name of the medicine.
     */
    public String getMedicineName() {
        return medicineName;
    }


    /**
     * Gets the quantity of the medicine requested for replenishment.
     * 
     * @return The quantity of the medicine.
     */
    public int getQuantity() {
        return quantity;
    }



    /**
     * Gets the current status of the replenishment request.
     * 
     * @return The current status of the request.
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status of the replenishment request.
     * 
     * @param status The new status of the request.
     */
    public void setStatus(String status) {
        this.status = status;
    }    
    
}
