
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
 * Класс MonitoringView представляет собой окно мониторинга состояния здоровья пациента.
 * 
 * Окно позволяет пользователю начинать и завершать мониторинг, отображает данные измерений
 * и результаты расчетов. Окно также предоставляет кнопку для выхода.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */

public class MonitoringView extends JFrame {

    private JTable dataTable;
    private JTable calculationsTable;
    private JButton startMonitoringButton;
    private JButton stopMonitoringButton;
    private JButton exitButton;
    private DefaultTableModel tableModel;
    private DefaultTableModel calculationsTableModel;
    private boolean monitoringActive = false;
    private boolean monitoringStarted = false; 

    /**
     * Конструктор для создания окна мониторинга.
     * 
     * @param controller Контроллер приложения для управления логикой мониторинга.
     */
    public MonitoringView(ApplicationController controller) {
        Patient patient = controller.getCurrentPatient();
        setTitle("Мониторинг: ID: " + patient.getId() + ", ФИО: " + patient.getFullname());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Время", "Параметр", "Значение"};
        tableModel = new DefaultTableModel(columnNames, 10) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dataTable = new JTable(tableModel);
        dataTable.setRowSelectionAllowed(false);
        dataTable.setColumnSelectionAllowed(false);
        dataTable.setCellSelectionEnabled(false);
        dataTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(780, 200));

        String[] calculationsColumnNames = {"Операция", "Температура", "Сердечный ритм", "Центральное венозное давление"};
        String[][] calculationsData = {
            {"Среднее арифметическое", "", "", ""},
            {"Математическое ожидание", "", "", ""},
            {"Дисперсия", "", "", ""},
            {"Первый квартиль", "", "", ""},
            {"Четвёртый квартиль", "", "", ""}
        };
        calculationsTableModel = new DefaultTableModel(calculationsData, calculationsColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        calculationsTable = new JTable(calculationsTableModel);
        calculationsTable.setRowSelectionAllowed(false);
        calculationsTable.setColumnSelectionAllowed(false);
        calculationsTable.setCellSelectionEnabled(false);
        calculationsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane calculationsTableScrollPane = new JScrollPane(calculationsTable);
        calculationsTableScrollPane.setPreferredSize(new Dimension(780, 150));

        startMonitoringButton = new JButton("Начало мониторинга");
        stopMonitoringButton = new JButton("Конец мониторинга");
        exitButton = new JButton("Выход");
        exitButton.setEnabled(true); 

        startMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startMonitoring();
                monitoringActive = true;
                monitoringStarted = true;
                exitButton.setEnabled(false); 
            }
        });

        stopMonitoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopMonitoring();
                monitoringActive = false;
                exitButton.setEnabled(true); 
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monitoringActive) {
                    JOptionPane.showMessageDialog(null, "Сначала завершите мониторинг");
                } else {
                    dispose();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startMonitoringButton);
        buttonPanel.add(stopMonitoringButton);
        buttonPanel.add(exitButton);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.weighty = 0.4;
        mainPanel.add(dataTableScrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.3;
        mainPanel.add(calculationsTableScrollPane, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        PatientObserver observer = new PatientObserver(tableModel, dataTable, calculationsTableModel, calculationsTable);
        controller.setPatientObserver(observer);

        pack();
        setVisible(true);
    }
}