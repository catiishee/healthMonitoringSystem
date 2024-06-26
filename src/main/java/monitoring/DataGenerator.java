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
 *
 * @author user
 */
public class DataGenerator {

    private TemperatureFactory temperetureFactory = new TemperatureFactory();
    private HeartRateFactory heartRateFactory = new HeartRateFactory();
    private CentralVenousPressureFactory pressureFactory = new CentralVenousPressureFactory();

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
