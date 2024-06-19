/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health;

/**
 *
 * @author user
 */
public class HealthIndicator {

    private double value;
    private boolean isCritical;

    public HealthIndicator(double value, boolean isCritical) {
        this.value = value;
        this.isCritical = isCritical;
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
