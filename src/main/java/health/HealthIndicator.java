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
public class HealthIndicator {

    private double value;
    private boolean isCritical;
    private LocalDateTime time;

    public HealthIndicator(double value, boolean isCritical) {
        this.value = value;
        this.isCritical = isCritical;
        this.time = LocalDateTime.now();
    }

    public HealthIndicator(double value, boolean isCritical, LocalDateTime time) {
        this.value = value;
        this.isCritical = isCritical;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isIsCritical() {
        return isCritical;
    }

    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }
    
    
}
