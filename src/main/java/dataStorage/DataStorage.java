/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStorage;

import human.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DataStorage {

    List<Patient> patients = new ArrayList<>();
    Patient currentPatient;

    public DataStorage(PatientSerializer patientSerializer) {
        try {
            patients = patientSerializer.loadPatients();
        } catch (IOException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }
    
    public Patient getPatientById(String id){
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

}
