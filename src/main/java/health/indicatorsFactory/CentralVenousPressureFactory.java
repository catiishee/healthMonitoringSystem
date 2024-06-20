/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.CentralVenousPressure;
import health.HealthIndicator;

/**
 *
 * @author user
 */
public class CentralVenousPressureFactory extends HealthIndicatorFactory<CentralVenousPressure> {

    public CentralVenousPressureFactory() {
        super(0, 20, 2, 12, 0.5, 2);
    }
    
    @Override
    public CentralVenousPressure createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinNormal() || newValue >= getMaxNormal();
        return new CentralVenousPressure(newValue, isCritical);
    }
    
    
}
