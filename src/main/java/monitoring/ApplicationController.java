/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoring;

import gui.PatientObserver;
import healthmonitoringsystem.DataStorage;
import human.Patient;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class ApplicationController {

    private DataGenerator dataGenerator = new DataGenerator();
    private Timer timer;
    private DataStorage storage = new DataStorage();
    private PatientObserver patientObserver;

    public void startMonitoring() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                measure();
            }
        }, 0, 5000);
    }

    public void stopMonitoring() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void createPatient(String id, String fullname) {
        Patient patient = new Patient(id, fullname);
        storage.setCurrentPatient(patient);
        storage.getPatients().add(patient);
    }

    public Patient getCurrentPatient() {
        return storage.getCurrentPatient();
    }

    private void measure() {
        Patient patient = getCurrentPatient();
        patient.addTemperature(dataGenerator.generateTemperature(patient));
        patient.addHeartRate(dataGenerator.generateHeartRate(patient));
        patient.addPressure(dataGenerator.generatePressure(patient));
        notify(patient);
    }

    public void setPatientObserver(PatientObserver patientObserver) {
        this.patientObserver = patientObserver;
        notify(getCurrentPatient());
    }
    
    private void notify(Patient patient){
        if(patientObserver != null){
            patientObserver.update(patient);
        }
    }
}
