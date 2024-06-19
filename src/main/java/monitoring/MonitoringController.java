/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoring;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class MonitoringController {
    private DataGenerator dataGenerator;
    private Timer timer;

    public MonitoringController(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    public void startMonitoring() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 5000); 
    }

    public void stopMonitoring() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
