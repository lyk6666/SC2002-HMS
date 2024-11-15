package scs1_group1.record;

/**
 * Represents a Medicine record, which contains information about the medicine's 
 * name, current stock, and alert threshold for replenishment.
 */
public class Medicine extends Record {
    private String medicineName;
    private int currentStock;
    private int alertThreshold;

    /**
     * Constructs a Medicine record with the specified name, stock level, and alert threshold.
     *
     * @param medicineName   The name of the medicine.
     * @param currentStock   The current stock level of the medicine.
     * @param alertThreshold The stock level at which an alert should be triggered.
     */
    public Medicine(String medicineName, int currentStock, int alertThreshold) {
        super();
        this.medicineName = medicineName;
        this.currentStock = currentStock;
        this.alertThreshold = alertThreshold;
    }


    /**
     * Returns a string representation of the medicine.
     *
     * @return The name of the medicine.
     */
    @Override
    public String toString() {
        return medicineName;
    }

    /**
     * Gets the name of the medicine.
     *
     * @return The name of the medicine.
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Sets the name of the medicine.
     *
     * @param medicineName The new name of the medicine.
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * Gets the current stock level of the medicine.
     *
     * @return The current stock level of the medicine.
     */
    public int getCurrentStock() {
        return currentStock;
    }

    /**
     * Sets the current stock level of the medicine.
     *
     * @param currentStock The new current stock level of the medicine.
     */
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }


    /**
     * Gets the alert threshold for the medicine.
     *
     * @return The alert threshold level of the medicine.
     */
    public int getAlertThreshold() {
        return alertThreshold;
    }

    /**
     * Sets the alert threshold for the medicine.
     *
     * @param alertThreshold The new alert threshold level for the medicine.
     */
    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }
}
