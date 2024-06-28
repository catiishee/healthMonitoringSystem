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
 *Класс для управления приложением мониторинга состояния здоровья пациентов.
 * Обеспечивает функциональность создания пациентов, начала и остановки мониторинга,
 * а также обновления наблюдателей пациентов.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class ApplicationController {

    //Объект для генерации данных (температура, сердечный ритм, давление)
    private DataGenerator dataGenerator = new DataGenerator();
    //Планировщик для запуска задач мониторинга в заданные интервалы времени
    private ScheduledExecutorService scheduler;
    //Хранилище данных пациентов
    private DataStorage storage;
    //Наблюдатель для отслеживания изменений состояния пациента
    private PatientObserver patientObserver;
    //Объект для сериализации и десериализации данных пациентов
    private PatientSerializer patientSerializer = new PatientSerializer();

    /**
     * Конструктор для создания контроллера приложения.
     * Инициализирует хранилище данных с использованием сериализатора пациентов.
     */
    public ApplicationController() {
        this.storage = new DataStorage(patientSerializer);
    }

    /**
     * Запускает процесс мониторинга состояния пациента.
     * Планировщик создает новую задачу, которая выполняется каждые 5 секунд и вызывает метод {@link #measure()}.
     */
    public void startMonitoring() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            measure();
        }, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * Останавливает процесс мониторинга состояния пациента.
     * Планировщик останавливается, и текущие данные пациента сохраняются с помощью сериализатора.
     */
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

    /**
     * Создает нового пациента и добавляет его в хранилище.
     * Устанавливает этого пациента как текущего.
     * 
     * @param id Идентификатор пациента.
     * @param fullname Полное имя пациента.
     */
    public void createPatient(String id, String fullname) {
        Patient patient = new Patient(id, fullname);
        storage.setCurrentPatient(patient);
        storage.getPatients().add(patient);
    }

    /**
     * Возвращает текущего пациента.
     * 
     * @return Текущий пациент.
     */
    public Patient getCurrentPatient() {
        return storage.getCurrentPatient();
    }

    /**
     * Проводит измерения состояния здоровья текущего пациента.
     * Генерирует новые значения температуры, сердечного ритма и давления,
     * обновляет их у пациента и уведомляет наблюдателя об изменениях.
     */
    private void measure() {
        Patient patient = getCurrentPatient();
        patient.addTemperature(dataGenerator.generateTemperature(patient));
        patient.addHeartRate(dataGenerator.generateHeartRate(patient));
        patient.addPressure(dataGenerator.generatePressure(patient));
        notify(patient);
    }

    /**
     * Устанавливает наблюдателя для пациента и сохраняет текущие данные пациента.
     * Уведомляет наблюдателя о текущих данных пациента.
     * 
     * @param patientObserver Наблюдатель для пациента.
     */
    public void setPatientObserver(PatientObserver patientObserver) {
        this.patientObserver = patientObserver;
        try {
            patientSerializer.savePatient(getCurrentPatient());
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notify(getCurrentPatient());
    }

    /**
     * Уведомляет наблюдателя об изменениях данных пациента.
     * 
     * @param patient Пациент, данные которого изменились.
     */
    private void notify(Patient patient) {
        if (patientObserver != null) {
            patientObserver.update(patient);
        }
    }

    /**
     * Загружает данные пациента по идентификатору и устанавливает его как текущего пациента.
     * 
     * @param id Идентификатор пациента.
     * @return Загруженный пациент.
     */
    public Patient loadPatient(String id) {
        Patient patient = storage.getPatientById(id);
        storage.setCurrentPatient(patient);
        return patient;
    }
}
