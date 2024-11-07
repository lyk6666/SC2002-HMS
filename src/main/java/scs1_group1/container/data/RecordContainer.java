package scs1_group1.container.data;

import java.util.HashMap;

import scs1_group1.container.Container;
import scs1_group1.record.Record;
public class RecordContainer implements Container {
    private final HashMap<Integer,Record> records;
    
    public RecordContainer() {
        records = new HashMap<>();
    }


    public HashMap<Integer,Record> getRecords() {
        return records;
    }

    public void putRecord(Record record) {
        records.put(record.getRecordId(), record);
    }

    public void removeRecord(int recordId) {
        if (records.containsKey(recordId))
            records.remove(recordId);
    }

    public Record getRecord(int recordId) {
        if (!records.containsKey(recordId))
            return null;
        return records.get(recordId);
    }

    public Boolean containsRecord(int recordId) {
        return records.containsKey(recordId);
    }

    public void clear() {
        records.clear();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i:records.keySet()) {
            result+=i+": "+records.get(i).toString()+"\n";
        }
        return result;
    }
}
