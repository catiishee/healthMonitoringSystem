/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStorage;

import human.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для хранения и управления данными пациентов.
 * Загружает данные пациентов при инициализации и предоставляет методы для получения,
 * установки и поиска пациентов.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class DataStorage {

    List<Patient> patients = new ArrayList<>();
    Patient currentPatient;

    /**
     * Конструктор для создания хранилища данных пациентов.
     * Загружает данные пациентов с использованием сериализатора.
     * 
     * @param patientSerializer объект для сериализации и десериализации данных пациентов.
     */
    public DataStorage(PatientSerializer patientSerializer) {
        try {
            patients = patientSerializer.loadPatients();
        } catch (IOException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Возвращает список всех пациентов.
     * 
     * @return список пациентов.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Устанавливает список пациентов.
     * 
     * @param patients список пациентов для установки.
     */
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Возвращает текущего пациента.
     * 
     * @return текущий пациент.
     */
    public Patient getCurrentPatient() {
        return currentPatient;
    }

    /**
     * Устанавливает текущего пациента.
     * 
     * @param currentPatient пациент для установки в качестве текущего.
     */
    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    /**
     * Ищет пациента по идентификатору и возвращает его.
     * 
     * @param id идентификатор пациента.
     * @return найденный пациент или null, если пациент не найден.
     */
    public Patient getPatientById(String id) {
        Optional<Patient> patient = patients.stream().filter(p -> p.getId().equals(id)).findFirst();
        return patient.orElse(null);
    }

}
