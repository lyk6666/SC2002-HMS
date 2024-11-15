package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Medicine;
import scs1_group1.record.Record;


/**
 * Container class for managing medicine records.
 * Provides functionalities to load, retrieve, and export medicine data.
 */
public class MedicineContainer extends RecordContainer {


    /**
     * Constructs a MedicineContainer and loads medicines from the given CSV file.
     * 
     * @param filePath Path to the CSV file containing medicine data.
     */
    public MedicineContainer(String filePath) {
        super();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true; // Flag to skip the header line

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the first line (header)
                    continue;
                }

                String[] fields = line.split(",");
                String medicineName = fields[0].trim();            // "Medicine Name"
                int currentStock = Integer.parseInt(fields[1].trim()); // "Initial Stock"
                int alertThreshold = Integer.parseInt(fields[2].trim()); // "Low Stock Level Alert"

                Medicine Medicine = new Medicine(medicineName, currentStock, alertThreshold);
                putRecord(Medicine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all medicines in the container.
     * 
     * @return A list of Medicine objects representing all medicines in the container.
     */
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        for (Record record : getRecords().values()) {
            if (record instanceof Medicine) {
                medicines.add((Medicine) record);
            }
        }
        return medicines;
    } 
    
    /**
     * Retrieves a medicine by its name.
     * 
     * @param medicineName The name of the medicine to retrieve.
     * @return The Medicine object if found, otherwise null.
     */
    public Medicine getMedicineByName(String medicineName) {
        for (Record record : getRecords().values()) {
            if (record instanceof Medicine) {
                Medicine medicine = (Medicine) record;
                if (medicine.getMedicineName().equals(medicineName)) {
                    return medicine;
                }
            }
        }
        return null;
    }

    /**
     * Exports all medicines in the container to a CSV file.
     * 
     * @param filePath The path of the CSV file to write to.
     */
    public void exportMedicineToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the CSV header
            writer.write("Medicine Name,Initial Stock,Low Stock Level Alert\n");

            // Write each medicine's details to the CSV file
            for (Medicine medicine : getAllMedicines()) {
                writer.write(String.format("%s,%d,%d\n",
                    medicine.getMedicineName(),
                    medicine.getCurrentStock(),
                    medicine.getAlertThreshold()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
