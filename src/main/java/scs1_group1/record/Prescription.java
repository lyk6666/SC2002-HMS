package scs1_group1.record;

public class Prescription extends Record {
    private String medicine;
    private int amount;
    private String status ;

    public Prescription(String medicine, int amount, String status) {
        super();  // Passes the unique ID to the Record superclass
        this.medicine = medicine;
        this.amount = amount;
        this.status = status;
    }

    public String getMedicine() {
        return medicine;
    }
    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}