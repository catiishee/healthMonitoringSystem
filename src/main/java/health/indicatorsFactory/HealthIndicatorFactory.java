/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import java.util.Random;

/**
 *
 * @author user
 * @param <T>
 */
public abstract class HealthIndicatorFactory<T extends HealthIndicator> {

    private double minCritical;
    private double maxCritical;
    private double minNormal;
    private double maxNormal;
    private double minStep;
    private double maxStep;

    public HealthIndicatorFactory(double minCritical, double maxCritical, double minNormal, double maxNormal, double minStep, double maxStep) {
        this.minCritical = minCritical;
        this.maxCritical = maxCritical;
        this.minNormal = minNormal;
        this.maxNormal = maxNormal;
        this.minStep = minStep;
        this.maxStep = maxStep;
    }
    
    public abstract T createIndicator(HealthIndicator healthIndicator);
    
    public T createIndicator(){
        double mean = (minNormal + maxNormal)/2;
        return createIndicator(new HealthIndicator(mean, false));
    }

    public double getMinCritical() {
        return minCritical;
    }

    public double getMaxCritical() {
        return maxCritical;
    }

    public double getMinNormal() {
        return minNormal;
    }

    public double getMaxNormal() {
        return maxNormal;
    }

    public double getMinStep() {
        return minStep;
    }

    public double getMaxStep() {
        return maxStep;
    }
    
    protected double newValue(HealthIndicator healthIndicator){
        Random random = new Random();
        double currentValue = healthIndicator.getValue();
        double step = getMinStep() + (getMaxStep() - getMinStep()) * random.nextDouble();
        boolean addStep = random.nextBoolean();

        double newValue;
        if (addStep) {
            newValue = currentValue + step;
        } else {
            newValue = currentValue - step;
        }

        if (newValue < getMinCritical()) {
            newValue = getMinCritical();
        } else if (newValue > getMaxCritical()) {
            newValue = getMaxCritical();
        }
        return newValue;
    }
}
