package scs1_group1.container.data;

import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Record;
import scs1_group1.record.ReplenishmentRequest;

public class ReplenishmentRequestContainer extends  RecordContainer{

    public ReplenishmentRequestContainer() {
        super();
    }

    public void addReplenishmentRequest(String medicineName, int quantity) {
        ReplenishmentRequest replenishmentRequest = new ReplenishmentRequest(medicineName, quantity);
        super.putRecord(replenishmentRequest);
    }

    public ReplenishmentRequest getReplenishmentRequestById(int recordId) {
        return (ReplenishmentRequest) super.getRecord(recordId);
    }

    public void updateReplenishmentRequestStatus(int recordId, String status) {
        ReplenishmentRequest replenishmentRequest = getReplenishmentRequestById(recordId);
        replenishmentRequest.setStatus(status);
    }

    public void removeReplenishmentRequest(int recordId) {
        super.removeRecord(recordId);
    }

    // Get all replenishment requests
    public List<ReplenishmentRequest> getAllReplenishmentRequests() {
        List<ReplenishmentRequest> requests = new ArrayList<>();
        for (Record record : getRecords().values()) {
            if (record instanceof ReplenishmentRequest) {
                requests.add((ReplenishmentRequest) record);
            }
        }
        return requests;
    }

    
}