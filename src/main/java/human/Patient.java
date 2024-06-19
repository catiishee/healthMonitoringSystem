/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package human;

import health.CentralVenousPressure;
import health.HeartRate;
import health.Temperature;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Patient {

    String id;
    String fullname;
    List<Temperature> temperatures = new ArrayList<>();
    List<HeartRate> heartRates = new ArrayList<>();
    List<CentralVenousPressure> pressures = new ArrayList<>();

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
        return temperatures;
    }

    public List<HeartRate> getHeartRates() {
        return heartRates;
    }

    public List<CentralVenousPressure> getPressures() {
        return pressures;
    }
    
    
}
