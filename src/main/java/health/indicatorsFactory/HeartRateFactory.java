/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import health.HeartRate;

/**
 *
 * @author user
 */
public class HeartRateFactory extends HealthIndicatorFactory<HeartRate> {

    public HeartRateFactory() {
        super(30, 180, 60, 100, 1, 5);
    }

    @Override
    public HeartRate createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinNormal() || newValue >= getMaxNormal();
        return new HeartRate(newValue, isCritical);
    }

}
