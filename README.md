# Hospital Management System (HMS)

This is a Java-based Hospital Management System (HMS) that manages patient records, doctor availability, appointment scheduling, prescriptions, and more. This project is structured using Maven as the build tool and includes Log4j2 for logging capabilities.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Usage](#usage)

---

## Features

- **User Roles**: Supports multiple roles including doctors, patients, and administrators.
- **Patient Management**: Patients can view and edit their personal information.
- **Appointment Management**: Schedule, confirm, and cancel appointments with doctors.
- **Medical Records**: Doctors can add consultation notes and prescriptions for patients.
- **Doctor Availability**: Doctors can set their availability for appointments.
- **Logging**: Integrated Log4j2 for error and event logging.

## Project Structure

The project follows a modular structure to separate concerns:

- `scs1_group1.Main`: Entry point of the application.
- `scs1_group1.menu`: Contains classes to manage user menus for each role (e.g., DoctorMenu, PatientMenu).
- `scs1_group1.user`: Classes representing different user roles like `Doctor`, `Patient`, and `Administrator`.
- `scs1_group1.record`: Handles medical records, appointment records, and prescriptions.
- `scs1_group1.container`: Manages data containers for users and records (e.g., `PatientContainer`, `AppointmentContainer`).

## Requirements

- **Java 17**
- **Maven**

## Dependencies

- **Log4j2**: For logging system events and errors.
  
These dependencies are specified in the `pom.xml` file and will be managed automatically by Maven.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/SunnyRaymond/SC2002-HMS.git
   cd HMS
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage

1. **Run the Application**:
   Start the application by running the `Main` class through Maven:
   ```bash
   mvn exec:java
   ```

2. **Interacting with the System**:
   - **Doctor**: Manage appointments, view patient medical records, and set availability.
   - **Patient**: Schedule and view appointments, view personal records, and edit contact information.
   - **Administrator**: Access all records and manage user accounts.

## Logging

Log4j2 is used to handle logging throughout the application. Logs are saved to a specified file (configured in `log4j2.xml`) and will help in debugging and monitoring the application's behavior.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
