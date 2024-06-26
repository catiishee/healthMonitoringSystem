/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package human;

import health.CentralVenousPressure;
import health.HeartRate;
import health.Temperature;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author user
 */
public class Patient {

    String id;
    String fullname;
    Deque<Temperature> temperatures = new ArrayDeque<>();
    Deque<HeartRate> heartRates = new ArrayDeque<>();
    Deque<CentralVenousPressure> pressures = new ArrayDeque<>();
    private static final int MAX_SIZE = 10;

    public Patient(String id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public List<Temperature> getTemperatures() {
        return new ArrayList<>(temperatures);
    }

    public List<HeartRate> getHeartRates() {
        return new ArrayList<>(heartRates);
    }

    public List<CentralVenousPressure> getPressures() {
        return new ArrayList<>(pressures);
    }

    public void addTemperature(Temperature measurement) {
        if (temperatures.size() == MAX_SIZE) {
            temperatures.removeFirst();
        }
        temperatures.addLast(measurement);
    }

    public void addHeartRate(HeartRate measurement) {
        if (heartRates.size() == MAX_SIZE) {
            heartRates.removeFirst();
        }
        heartRates.addLast(measurement);
    }

    public void addPressure(CentralVenousPressure measurement) {
        if (pressures.size() == MAX_SIZE) {
            pressures.removeFirst();
        }
        pressures.addLast(measurement);
    }

    public Temperature getLastTemperature() {
        if (temperatures.isEmpty()) {
            return null;
        }
        return temperatures.getLast();
    }

    public HeartRate getLastHeartRate() {
        if (heartRates.isEmpty()) {
            return null;
        }
        return heartRates.getLast();
    }

    public CentralVenousPressure getLastPressure() {
        if (pressures.isEmpty()) {
            return null;
        }
        return pressures.getLast();
    }
    
    public Temperature getLastCriticalTemperatureStart() {
        List<Temperature> tempList = new ArrayList<>(temperatures);
        for (int i = tempList.size() - 1; i > 0; i--) {
            if (tempList.get(i).isIsCritical() && !tempList.get(i - 1).isIsCritical()) {
                return tempList.get(i);
            }
        }
        if(tempList.get(0).isIsCritical()){
            return tempList.get(0);
        }
        return null;
    }

    public HeartRate getLastCriticalHeartRateStart() {
        List<HeartRate> hrList = new ArrayList<>(heartRates);
        for (int i = hrList.size() - 1; i > 0; i--) {
            if (hrList.get(i).isIsCritical() && !hrList.get(i - 1).isIsCritical()) {
                return hrList.get(i);
            }
        }
        if(hrList.get(0).isIsCritical()){
            return hrList.get(0);
        }
        return null;
    }

    public CentralVenousPressure getLastCriticalPressureStart() {
        List<CentralVenousPressure> cvpList = new ArrayList<>(pressures);
        for (int i = cvpList.size() - 1; i > 0; i--) {
            if (cvpList.get(i).isIsCritical() && !cvpList.get(i - 1).isIsCritical()) {
                return cvpList.get(i);
            }
        }
        if(cvpList.get(0).isIsCritical()){
            return cvpList.get(0);
        }
        return null;
    }


}
