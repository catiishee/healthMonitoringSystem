/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class HeartRate extends HealthIndicator {
    
    public HeartRate(double value, boolean isCritical) {
        super(value, isCritical);
    }
    
    public HeartRate(double value, boolean isCritical, LocalDateTime time) {
        super(value, isCritical, time);
    }
    
}
