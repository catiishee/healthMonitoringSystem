/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package healthmonitoringsystem;

import gui.MainView;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class HealthMonitoringSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
}
