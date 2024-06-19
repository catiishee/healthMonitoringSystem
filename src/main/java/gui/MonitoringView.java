
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import human.Patient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import monitoring.DataGenerator;
import monitoring.MonitoringController;


/**
 *
 * @author user
 */
public class MonitoringView extends JFrame {
    private Patient patient;
    private JTextArea dataArea;
    private JButton startMonitoringButton;
    private JButton stopMonitoringButton;
    private MonitoringController monitoringController;

    public MonitoringView(Patient patient) {
        this.patient = patient;
        setTitle("Monitoring: " + patient.getFullname());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        dataArea = new JTextArea();
        dataArea.setEditable(false);

        startMonitoringButton = new JButton("Start Monitoring");
        stopMonitoringButton = new JButton("Stop Monitoring");

        monitoringController = new MonitoringController(new DataGenerator());

        startMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitoringController.startMonitoring();
            }
        });

        stopMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitoringController.stopMonitoring();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startMonitoringButton);
        buttonPanel.add(stopMonitoringButton);

        add(new JScrollPane(dataArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
