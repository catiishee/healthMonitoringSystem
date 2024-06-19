/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import health.Temperature;

/**
 *
 * @author user
 */
public class TemperatureFactory extends HealthIndicatorFactory {

    public TemperatureFactory() {
        super(35.0, 40.0, 35.5, 37.0, 0.1, 0.5);
    }

    @Override
    public HealthIndicator createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinCritical() || newValue >= getMaxCritical();
        return new Temperature(newValue, isCritical);
    }
     
}
