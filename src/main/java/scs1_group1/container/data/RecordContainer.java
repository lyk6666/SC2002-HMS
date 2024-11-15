package scs1_group1.container.data;

import java.util.HashMap;

import scs1_group1.container.Container;
import scs1_group1.record.Record;

/**
 * Container class for managing general records.
 * Provides functionalities to add, remove, retrieve, and manage records in a HashMap.
 */
public class RecordContainer implements Container {
    private final HashMap<Integer,Record> records;
    

    /**
     * Constructs a new RecordContainer to manage records.
     */
    public RecordContainer() {
        records = new HashMap<>();
    }


    /**
     * Retrieves all records in the container.
     * 
     * @return A HashMap containing all records.
     */
    public HashMap<Integer,Record> getRecords() {
        return records;
    }

    /**
     * Adds a record to the container.
     * 
     * @param record The record to be added.
     */
    public void putRecord(Record record) {
        records.put(record.getRecordId(), record);
    }

    /**
     * Removes a record from the container by its record ID.
     * 
     * @param recordId The ID of the record to be removed.
     */
    public void removeRecord(int recordId) {
        if (records.containsKey(recordId))
            records.remove(recordId);
    }

    /**
     * Retrieves a record from the container by its record ID.
     * 
     * @param recordId The ID of the record to be retrieved.
     * @return The Record object if found, otherwise null.
     */
    public Record getRecord(int recordId) {
        if (!records.containsKey(recordId))
            return null;
        return records.get(recordId);
    }


    /**
     * Checks if a record with a specific ID exists in the container.
     * 
     * @param recordId The ID of the record to be checked.
     * @return True if the record exists, otherwise false.
     */
    public Boolean containsRecord(int recordId) {
        return records.containsKey(recordId);
    }

    /**
     * Clears all records from the container.
     */
    public void clear() {
        records.clear();
    }

    /**
     * Returns a string representation of all records in the container.
     * 
     * @return A string listing all records by their ID and details.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i:records.keySet()) {
            result+=i+": "+records.get(i).toString()+"\n";
        }
        return result;
    }
}
