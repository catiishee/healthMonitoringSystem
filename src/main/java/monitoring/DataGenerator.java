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
    
    public Temperature generateTemperature(Patient patient){
        Temperature temperature = patient.getLastTemperature();
        if(temperature == null){
            return temperetureFactory.createIndicator();
        } 
        return temperetureFactory.createIndicator(temperature);
    }
    
    public HeartRate generateHeartRate(Patient patient){
        HeartRate heartRate = patient.getLastHeartRate();
        if(heartRate == null){
            return heartRateFactory.createIndicator();
        } 
        return heartRateFactory.createIndicator(heartRate);
        
    }
    
    public CentralVenousPressure generatePressure(Patient patient){
        CentralVenousPressure pressure = patient.getLastPressure();
        if(pressure == null){
            return pressureFactory.createIndicator();
        } 
        return pressureFactory.createIndicator(pressure);
        
    }
}
