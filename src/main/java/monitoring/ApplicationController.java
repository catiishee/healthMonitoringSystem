/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoring;

import gui.PatientObserver;
import dataStorage.DataStorage;
import dataStorage.PatientSerializer;
import human.Patient;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ApplicationController {

    private DataGenerator dataGenerator = new DataGenerator();
    private ScheduledExecutorService scheduler;
    private DataStorage storage;
    private PatientObserver patientObserver;
    private PatientSerializer patientSerializer = new PatientSerializer();

    public ApplicationController() {
        this.storage  = new DataStorage(patientSerializer);
    }
    
    public void startMonitoring() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            measure();
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void stopMonitoring() {
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                patientSerializer.savePatient(getCurrentPatient());
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        try {
                patientSerializer.savePatient(getCurrentPatient());
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        notify(getCurrentPatient());
    }
    
    private void notify(Patient patient){
        if(patientObserver != null){
            patientObserver.update(patient);
        }
    }
    
    public Patient loadPatient(String id){
        Patient patient = storage.getPatientById(id);
        storage.setCurrentPatient(patient);
        return patient;
    }
}
