package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Medicine;
import scs1_group1.record.Record;

public class MedicineContainer extends RecordContainer {

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

    // Method to get a list of all medicines in the container
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        for (Record record : getRecords().values()) {
            if (record instanceof Medicine) {
                medicines.add((Medicine) record);
            }
        }
        return medicines;
    } 
    
    //since medicine name is unique, we can use this method to get the medicine object by its name
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
    
}
