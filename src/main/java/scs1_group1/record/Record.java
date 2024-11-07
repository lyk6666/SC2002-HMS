package scs1_group1.record;

public abstract class Record {
    private static int globalRecordCounter = 0; // Shared counter for all records
    private final int recordId;

    public Record() {
        this.recordId = globalRecordCounter++; // Increment counter for each new instance
    }

    public int getRecordId() {
        return recordId;
    }

    @Override
    public String toString() {
        return "" + recordId;
    }
}
