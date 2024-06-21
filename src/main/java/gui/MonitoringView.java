
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
import javax.swing.table.DefaultTableModel;
import monitoring.ApplicationController;

/**
 *
 * @author user
 */
public class MonitoringView extends JFrame {

    private JTable dataTable;
    private JButton startMonitoringButton;
    private JButton stopMonitoringButton;
    private ApplicationController controller;
    private DefaultTableModel tableModel;

    public MonitoringView(ApplicationController controller) {
        this.controller = controller;
        Patient patient = controller.getCurrentPatient();
        setTitle("Мониторинг: ID: " + patient.getId() + ", ФИО: " + patient.getFullname());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Время", "Параметр", "Значение"};
        tableModel = new DefaultTableModel(columnNames, 10) {
            @Override
            public boolean isCellEditable(int row, int column
            ) {
                return false;
            }
        };

        dataTable = new JTable(tableModel);
        dataTable.setRowSelectionAllowed(false);
        dataTable.setColumnSelectionAllowed(false);
        dataTable.setCellSelectionEnabled(false);
        dataTable.getTableHeader().setReorderingAllowed(false);
        PatientObserver observer = new PatientObserver(tableModel, dataTable);
        controller.setPatientObserver(observer);

        startMonitoringButton = new JButton("Начало мониторинга");
        stopMonitoringButton = new JButton("Конец мониторинга");

        startMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startMonitoring();
            }
        });

        stopMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopMonitoring();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startMonitoringButton);
        buttonPanel.add(stopMonitoringButton);

        add(new JScrollPane(dataTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
