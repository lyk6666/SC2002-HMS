package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Record;
import scs1_group1.record.ReplenishmentRequest;


/**
 * Container class for managing replenishment requests.
 * Provides functionalities to load, add, retrieve, update, remove, and export replenishment requests.
 */
public class ReplenishmentRequestContainer extends RecordContainer {


    /**
     * Constructs a ReplenishmentRequestContainer and loads replenishment requests from the given CSV file.
     *
     * @param file_path Path to the CSV file containing replenishment request data.
     */
    public ReplenishmentRequestContainer(String file_path) {
        super();
        loadReplenishmentRequests(file_path);
    }


    /**
     * Loads replenishment requests from the specified CSV file into the container.
     *
     * @param filePath Path to the CSV file.
     */
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

    /**
     * Adds a new replenishment request to the container.
     *
     * @param medicineName Name of the medicine.
     * @param quantity Quantity of the medicine requested.
     */
    public void addReplenishmentRequest(String medicineName, int quantity) {
        ReplenishmentRequest replenishmentRequest = new ReplenishmentRequest(medicineName, quantity, "Pending");
        super.putRecord(replenishmentRequest);
    }


    /**
     * Retrieves a replenishment request by its record ID.
     *
     * @param recordId The ID of the replenishment request to retrieve.
     * @return The ReplenishmentRequest object if found, otherwise null.
     */
    public ReplenishmentRequest getReplenishmentRequestById(int recordId) {
        return (ReplenishmentRequest) super.getRecord(recordId);
    }


    /**
     * Updates the status of a replenishment request.
     *
     * @param recordId The ID of the replenishment request to update.
     * @param status The new status to set for the replenishment request.
     */
    public void updateReplenishmentRequestStatus(int recordId, String status) {
        ReplenishmentRequest replenishmentRequest = getReplenishmentRequestById(recordId);
        replenishmentRequest.setStatus(status);
    }

    /**
     * Removes a replenishment request from the container by its record ID.
     *
     * @param recordId The ID of the replenishment request to be removed.
     */
    public void removeReplenishmentRequest(int recordId) {
        super.removeRecord(recordId);
    }

    /**
     * Retrieves all replenishment requests in the container.
     *
     * @return A list of all ReplenishmentRequest objects in the container.
     */
    public List<ReplenishmentRequest> getAllReplenishmentRequests() {
        List<ReplenishmentRequest> requests = new ArrayList<>();
        for (Record record : getRecords().values()) {
            if (record instanceof ReplenishmentRequest) {
                requests.add((ReplenishmentRequest) record);
            }
        }
        return requests;
    }

    /**
     * Exports all replenishment requests to a CSV file.
     *
     * @param filePath The path to the CSV file to which the replenishment requests will be exported.
     */
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