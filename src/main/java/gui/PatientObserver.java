/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import health.CentralVenousPressure;
import health.HealthIndicator;
import health.HeartRate;
import health.Temperature;
import human.Patient;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class PatientObserver implements DataObserver<Patient> {

    private final DefaultTableModel tableModel;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.0");
    private JTable jTable;

    public PatientObserver(DefaultTableModel tableModel, JTable jTable) {
        this.tableModel = tableModel;
        this.jTable = jTable;
    }

    @Override
    public void update(Patient patient) {
        List<Temperature> temperatures = patient.getTemperatures();
        List<HeartRate> heartRates = patient.getHeartRates();
        List<CentralVenousPressure> pressures = patient.getPressures();

        int maxRows = 10;
        int rowCount = Math.max(Math.max(temperatures.size(), heartRates.size()), pressures.size());

        tableModel.setRowCount(maxRows);
        tableModel.setColumnCount(3);
        tableModel.setColumnIdentifiers(new String[]{"Temperature", "Heart Rate", "Pressure"});

        for (int i = 0; i < maxRows; i++) {
            if (i < rowCount) {
                if (i < temperatures.size()) {
                    HealthIndicator temperature = temperatures.get(i);
                    tableModel.setValueAt(DECIMAL_FORMAT.format(temperature.getValue()), i, 0);
                } else {
                    tableModel.setValueAt("-", i, 0);
                }
                if (i < heartRates.size()) {
                    HealthIndicator heartRate = heartRates.get(i);
                    tableModel.setValueAt(DECIMAL_FORMAT.format(heartRate.getValue()), i, 1);
                } else {
                    tableModel.setValueAt("-", i, 1);
                }
                if (i < pressures.size()) {
                    HealthIndicator pressure = pressures.get(i);
                    tableModel.setValueAt(DECIMAL_FORMAT.format(pressure.getValue()), i, 2);
                } else {
                    tableModel.setValueAt("-", i, 2);
                }
            } else {
                tableModel.setValueAt("-", i, 0);
                tableModel.setValueAt("-", i, 1);
                tableModel.setValueAt("-", i, 2);
            }
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                HealthIndicator indicator = null;

                if (column == 0 && row < temperatures.size()) {
                    indicator = temperatures.get(row);
                } else if (column == 1 && row < heartRates.size()) {
                    indicator = heartRates.get(row);
                } else if (column == 2 && row < pressures.size()) {
                    indicator = pressures.get(row);
                }

                if (indicator != null && indicator.isIsCritical()) {
                    cell.setBackground(Color.RED);
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        };

        jTable.setDefaultRenderer(Object.class, cellRenderer);
        tableModel.fireTableDataChanged();
    }
}


