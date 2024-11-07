package scs1_group1.record;

public class Medicine extends Record {
    private String medicineName;
    private int currentStock;
    private int alertThreshold;

    public Medicine(String medicineName, int currentStock, int alertThreshold) {
        super();
        this.medicineName = medicineName;
        this.currentStock = currentStock;
        this.alertThreshold = alertThreshold;
    }


    @Override
    public String toString() {
        return medicineName;
    }

    //Medicine Name
    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    //Current Stock
    public int getCurrentStock() {
        return currentStock;
    }
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    //Alert Threshold
    public int getAlertThreshold() {
        return alertThreshold;
    }
    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }
}
