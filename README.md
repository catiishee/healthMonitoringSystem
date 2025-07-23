# Health Monitoring System

## Overview
This project is a synthetic health monitoring system developed as part of a course on Web Application Programming at the National Research Nuclear University MEPhI. The system tracks key health indicators such as temperature, heart rate, and central venous pressure, providing real-time data monitoring and analysis. The program features a graphical user interface (GUI) and operates in a multithreaded environment to ensure continuous data generation and alerting on critical conditions.

## Objectives
- **Develop a health monitoring system** that tracks vital signs including temperature, heart rate, and central venous pressure.
- **Implement a user-friendly GUI** to manage patient data and visualize health metrics.
- **Provide real-time alerts** for critical health states, ensuring timely notification of abnormal conditions.

## Key Features
- **Patient Management**: Create new patients or select existing ones for monitoring. Patient data is stored and loaded from log files for easy access and review.
- **Real-Time Monitoring**: Continuously generates health data every 5 seconds, with smooth transitions and occasional shifts to critical states, reflecting realistic health changes.
- **Critical Alerts**: Highlights abnormal values in red and provides notifications when any parameter deviates from the normal range.
- **Statistical Analysis**: Calculates mean, mathematical expectation, variance, and quartiles for each health metric, providing comprehensive statistical insights.

## System Functionality
1. **Main Menu**: Features buttons for "New Patient," "Open Patient," and "Exit."
2. **Create Patient**: Allows input of patient ID and full name, creating a new patient profile.
3. **Open Patient**: Displays a list of existing patients for selection and review.
4. **Monitoring Window**: Shows the last 10 measurements of temperature, heart rate, and venous pressure for the selected patient. Includes "Start Monitoring" and "Stop Monitoring" buttons.
5. **Data Generation**: Health data is generated at regular intervals when monitoring is active, with values stored and analyzed in real-time.

## Methodology
- **ER Diagram**: Defines the relationships between entities in the system.
- **Use-Case Diagram**: Illustrates key user interactions, including creating and opening patients, starting and stopping monitoring, and responding to critical health alerts.
- **Class Diagram**: Outlines the structure and associations between classes, including `HealthIndicator`, `Patient`, `MonitoringView`, `DataGenerator`, and `PatientObserver`.

## Technology Stack
- **Java**: Core programming language used for backend logic and GUI implementation.
- **Swing**: For creating the graphical user interface components.
- **Multithreading**: Ensures smooth and continuous data generation and processing.
- **GitHub**: Version control managed through Git with significant commits marking each project milestone.

## Running the System
1. Clone the repository from GitHub: [healthMonitoringSystem](https://github.com/catiishee/healthMonitoringSystem).
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Compile and run the main class to launch the health monitoring system.

## Key Components
- **ApplicationController**: Manages the main application flow, handling patient creation, data loading, and monitoring control.
- **DataGenerator**: Generates synthetic health data for each patient, simulating real-time monitoring conditions.
- **PatientSerializer**: Handles saving and loading patient data from log files.
- **Statistics**: Provides statistical calculations on health indicators, including mean, variance, and quartiles.

## Project Structure
- **GUI**: Contains all interface-related classes (`MainView`, `PatientView`, `MonitoringView`).
- **Health**: Defines health indicators and data generation methods.
- **Monitoring**: Controls the monitoring logic and data flow between components.
- **DataStorage**: Manages storage and retrieval of patient data.
- **Statistics**: Performs calculations on health data to derive meaningful insights.

## Acknowledgments
- Developed under the guidance of Dmitry Sergeyevich Smirnov and Petr Vladimirovich Bochkarev, as part of the curriculum at MEPhI.

