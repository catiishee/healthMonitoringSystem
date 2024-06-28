/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoring;

import health.CentralVenousPressure;
import health.HeartRate;
import health.Temperature;
import health.indicatorsFactory.CentralVenousPressureFactory;
import health.indicatorsFactory.HeartRateFactory;
import health.indicatorsFactory.TemperatureFactory;
import human.Patient;

/**
 * Класс для генерации новых данных состояния здоровья пациента.
 * Генерирует значения температуры, сердечного ритма и центрального венозного давления.
 * Использует фабрики для создания новых значений этих показателей.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class DataGenerator {

    private TemperatureFactory temperetureFactory = new TemperatureFactory();
    private HeartRateFactory heartRateFactory = new HeartRateFactory();
    private CentralVenousPressureFactory pressureFactory = new CentralVenousPressureFactory();

    /**
     * Генерирует новое значение температуры для пациента.
     * 
     * @param patient Пациент, для которого генерируется значение температуры.
     * @return Новое значение температуры.
     */
    public Temperature generateTemperature(Patient patient) {
        Temperature result;
        Temperature previous = patient.getLastTemperature();
        if (previous == null) {
            result = temperetureFactory.createIndicator();
        } else {
            result = temperetureFactory.createIndicator(previous);
        }
        return result;
    }

    /**
     * Генерирует новое значение сердечного ритма для пациента.
     * 
     * @param patient Пациент, для которого генерируется значение сердечного ритма.
     * @return Новое значение сердечного ритма.
     */
    public HeartRate generateHeartRate(Patient patient) {
        HeartRate result;
        HeartRate previous = patient.getLastHeartRate();
        if (previous == null) {
            result = heartRateFactory.createIndicator();
        } else {
            result = heartRateFactory.createIndicator(previous);
        }
        return result;
    }

    /**
     * Генерирует новое значение центрального венозного давления для пациента.
     * 
     * @param patient Пациент, для которого генерируется значение центрального венозного давления.
     * @return Новое значение центрального венозного давления.
     */
    public CentralVenousPressure generatePressure(Patient patient) {
        CentralVenousPressure result;
        CentralVenousPressure previous = patient.getLastPressure();
        if (previous == null) {
            result = pressureFactory.createIndicator();
        } else {
            result = pressureFactory.createIndicator(previous);
        }
        return result;
    }
}
