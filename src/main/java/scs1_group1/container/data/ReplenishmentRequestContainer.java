package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Record;
import scs1_group1.record.ReplenishmentRequest;

public class ReplenishmentRequestContainer extends RecordContainer {


    public ReplenishmentRequestContainer(String file_path) {
        super();
        loadReplenishmentRequests(file_path);
    }

    private void loadReplenishmentRequests(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header line
                    continue;
                }

                String[] fields = line.split(",");
                String medicineName = fields[0].trim();
                int quantity = Integer.parseInt(fields[1].trim());
                String status = fields[2].trim();

                ReplenishmentRequest request = new ReplenishmentRequest(medicineName, quantity, status);
                super.putRecord(request); // Add to container
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addReplenishmentRequest(String medicineName, int quantity) {
        ReplenishmentRequest replenishmentRequest = new ReplenishmentRequest(medicineName, quantity, "Pending");
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

    // Export all replenishment requests to CSV
    public void exportReplenishmentRequestToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.write("MedicineName,Quantity,Status\n");

            // Write each replenishment request's details
            for (ReplenishmentRequest request : getAllReplenishmentRequests()) {
                writer.write(String.format("%s,%d,%s\n",
                    request.getMedicineName(),
                    request.getQuantity(),
                    request.getStatus()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}