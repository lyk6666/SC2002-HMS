package scs1_group1.record;

/**
 * Represents a prescription for a specific medicine with details including 
 * medicine name, prescribed amount, and the status of the prescription.
 */
public class Prescription extends Record {
    private String medicine;
    private int amount;
    private String status ;

    /**
     * Constructs a Prescription record with the specified medicine name, amount, and status.
     *
     * @param medicine The name of the medicine being prescribed.
     * @param amount   The amount of the medicine prescribed.
     * @param status   The status of the prescription (e.g., "Pending", "Dispensed").
     */
    public Prescription(String medicine, int amount, String status) {
        super();  // Passes the unique ID to the Record superclass
        this.medicine = medicine;
        this.amount = amount;
        this.status = status;
    }

    /**
     * Gets the name of the medicine prescribed.
     *
     * @return The name of the medicine.
     */
    public String getMedicine() {
        return medicine;
    }

    /**
     * Sets the name of the medicine prescribed.
     *
     * @param medicine The new name of the medicine.
     */
    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    /**
     * Gets the amount of the medicine prescribed.
     *
     * @return The amount of the medicine.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the medicine prescribed.
     *
     * @param amount The new amount of the medicine.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the current status of the prescription.
     *
     * @return The status of the prescription (e.g., "Pending", "Dispensed").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the prescription.
     *
     * @param status The new status of the prescription.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}