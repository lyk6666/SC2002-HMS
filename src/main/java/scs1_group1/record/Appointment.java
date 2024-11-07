package scs1_group1.record;

public class Appointment extends Record {
    private String time;
    private String patientHospitalId;
    private String doctorHospitalId;
    private String status = "pending";

    public Appointment(String time, String patientHospitalId, String doctorHospitalId) {
        super();
        this.time = time;
        this.patientHospitalId = patientHospitalId;
        this.doctorHospitalId = doctorHospitalId;
    }

    @Override
    public String toString() {
        return time;
    }

    //Time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {   // Setter method for time
        this.time = time;
    }

    //Patient Record ID
    public String getpatientHospitalId() {
        return patientHospitalId;
    }

    public void setpatientHospitalId(String patientHospitalId) {   // Setter method for patientHospitalId
        this.patientHospitalId = patientHospitalId;
    }

    //Doctor Record ID

    public String getdoctorHospitalId() {
        return doctorHospitalId;
    }
    public void setdoctorHospitalId(String doctorHospitalId) {   // Setter method for doctorHospitalId
        this.doctorHospitalId = doctorHospitalId;
    }

    //Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {   // Setter method for status
        this.status = status;
    }
}
