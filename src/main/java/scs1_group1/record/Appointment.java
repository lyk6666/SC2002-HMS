package scs1_group1.record;

/**
 * Represents an appointment between a patient and a doctor in the hospital system.
 * Contains details about the appointment time, patient, doctor, status, and a unique identifier.
 */
public class Appointment extends Record {
    private String time;
    private String patientHospitalId;
    private String doctorHospitalId;
    private String status ;
    private String appointmentIdentifyId;//a unique id for each appointment, constructed by the patientHospitalId+doctorHospitalId+time


    /**
     * Constructs an Appointment instance with the specified time, patient ID, doctor ID, and status.
     *
     * @param time             The time of the appointment in the format "YYYY-MM-DD HH:MM".
     * @param patientHospitalId The hospital ID of the patient involved in the appointment.
     * @param doctorHospitalId  The hospital ID of the doctor involved in the appointment.
     * @param status            The status of the appointment (e.g., "pending", "confirmed", "completed").
     */
    public Appointment(String time, String patientHospitalId, String doctorHospitalId, String status) {
        super();
        this.time = time;
        this.patientHospitalId = patientHospitalId;
        this.doctorHospitalId = doctorHospitalId;
        this.status = status;
        this.appointmentIdentifyId = patientHospitalId + doctorHospitalId + time;
    }

    /**
     * Returns a string representation of the appointment, primarily showing the time.
     *
     * @return A string representing the time of the appointment.
     */
    @Override
    public String toString() {
        return time;
    }

    /**
     * Returns the unique identifier of the appointment.
     *
     * @return The appointment's unique identifier, constructed from patientHospitalId, doctorHospitalId, and time.
     */
    public String getAppointmentIdentifyId() {
        return appointmentIdentifyId;
    }

    /**
     * Returns the time of the appointment.
     *
     * @return The appointment time in the format "YYYY-MM-DD HH:MM".
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the appointment.
     *
     * @param time The new appointment time in the format "YYYY-MM-DD HH:MM".
     */
    public void setTime(String time) {   // Setter method for time
        this.time = time;
    }

    /**
     * Returns the hospital ID of the patient involved in the appointment.
     *
     * @return The patient hospital ID.
     */
    public String getpatientHospitalId() {
        return patientHospitalId;
    }

    /**
     * Sets the hospital ID of the patient involved in the appointment.
     *
     * @param patientHospitalId The new patient hospital ID.
     */
    public void setpatientHospitalId(String patientHospitalId) {   // Setter method for patientHospitalId
        this.patientHospitalId = patientHospitalId;
    }


    /**
     * Returns the hospital ID of the doctor involved in the appointment.
     *
     * @return The doctor hospital ID.
     */
    public String getdoctorHospitalId() {
        return doctorHospitalId;
    }

    /**
     * Sets the hospital ID of the doctor involved in the appointment.
     *
     * @param doctorHospitalId The new doctor hospital ID.
     */
    public void setdoctorHospitalId(String doctorHospitalId) {   // Setter method for doctorHospitalId
        this.doctorHospitalId = doctorHospitalId;
    }

    /**
     * Returns the status of the appointment.
     *
     * @return The appointment status (e.g., "pending", "confirmed", "completed").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status The new status of the appointment (e.g., "pending", "confirmed", "completed").
     */
    public void setStatus(String status) {   // Setter method for status
        this.status = status;
    }
}
