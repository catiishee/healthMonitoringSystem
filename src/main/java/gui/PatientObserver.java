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
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import statistics.Statistics;

/**
 *
 * @author user
 */
public class PatientObserver implements DataObserver<Patient> {

    private final DefaultTableModel tableModel;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");
    private JTable jTable;
    private JDialog previousDialog;
    private DefaultTableModel calculationsTableModel;
    private JTable calculationsTable;

    public PatientObserver(DefaultTableModel tableModel, JTable jTable, DefaultTableModel calculationsTableModel, JTable calculationsTable) {
        this.tableModel = tableModel;
        this.jTable = jTable;
        this.calculationsTableModel = calculationsTableModel;
        this.calculationsTable = calculationsTable;
    }

    @Override
    public void update(Patient patient) {
        SwingUtilities.invokeLater(() -> {
            List<Temperature> temperatures = patient.getTemperatures();
            List<HeartRate> heartRates = patient.getHeartRates();
            List<CentralVenousPressure> pressures = patient.getPressures();

            int maxRows = 10;
            int rowCount = Math.max(Math.max(temperatures.size(), heartRates.size()), pressures.size());

            tableModel.setRowCount(maxRows);
            tableModel.setColumnCount(3);
            tableModel.setColumnIdentifiers(new String[]{"Температура", "Сердечный ритм", "Центральное венозное давление"});

            for (int i = 0; i < maxRows; i++) {
                if (i < rowCount) {
                    if (i < temperatures.size()) {
                        HealthIndicator temperature = temperatures.get(i);
                        tableModel.setValueAt(formatValue(temperature, temperatures, i), i, 0);
                    } else {
                        tableModel.setValueAt("-", i, 0);
                    }
                    if (i < heartRates.size()) {
                        HealthIndicator heartRate = heartRates.get(i);
                        tableModel.setValueAt(formatValue(heartRate, heartRates, i), i, 1);
                    } else {
                        tableModel.setValueAt("-", i, 1);
                    }
                    if (i < pressures.size()) {
                        HealthIndicator pressure = pressures.get(i);
                        tableModel.setValueAt(formatValue(pressure, pressures, i), i, 2);
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
            jTable.revalidate();
            jTable.repaint();

            updateCalculationsTable(temperatures, heartRates, pressures);

            checkAndNotify(temperatures, heartRates, pressures);
        });
    }

    private void updateCalculationsTable(List<Temperature> temperatures, List<HeartRate> heartRates, List<CentralVenousPressure> pressures) {
    calculationsTableModel.setValueAt("Среднее арифмитическое", 0, 0);
    calculationsTableModel.setValueAt(temperatures.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(temperatures)), 0, 1);
    calculationsTableModel.setValueAt(heartRates.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(heartRates)), 0, 2);
    calculationsTableModel.setValueAt(pressures.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(pressures)), 0, 3);

    calculationsTableModel.setValueAt("Математическое ожидание", 1, 0);
    calculationsTableModel.setValueAt(temperatures.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(temperatures)), 1, 1); 
    calculationsTableModel.setValueAt(heartRates.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(heartRates)), 1, 2); 
    calculationsTableModel.setValueAt(pressures.isEmpty() ? "-" : formatDouble(Statistics.calculateMean(pressures)), 1, 3); 

    calculationsTableModel.setValueAt("Дисперсия", 2, 0);
    calculationsTableModel.setValueAt(temperatures.isEmpty() ? "-" : formatDouble(Statistics.calculateVariance(temperatures)), 2, 1);
    calculationsTableModel.setValueAt(heartRates.isEmpty() ? "-" : formatDouble(Statistics.calculateVariance(heartRates)), 2, 2);
    calculationsTableModel.setValueAt(pressures.isEmpty() ? "-" : formatDouble(Statistics.calculateVariance(pressures)), 2, 3);

    calculationsTableModel.setValueAt("Первый квартиль", 3, 0);
    calculationsTableModel.setValueAt(temperatures.isEmpty() ? "-" : formatDouble(Statistics.calculateFirstQuartile(temperatures)), 3, 1);
    calculationsTableModel.setValueAt(heartRates.isEmpty() ? "-" : formatDouble(Statistics.calculateFirstQuartile(heartRates)), 3, 2);
    calculationsTableModel.setValueAt(pressures.isEmpty() ? "-" : formatDouble(Statistics.calculateFirstQuartile(pressures)), 3, 3);

    calculationsTableModel.setValueAt("Четвёртый квартиль", 4, 0);
    calculationsTableModel.setValueAt(temperatures.isEmpty() ? "-" : formatDouble(Statistics.calculateFourthQuartile(temperatures)), 4, 1);
    calculationsTableModel.setValueAt(heartRates.isEmpty() ? "-" : formatDouble(Statistics.calculateFourthQuartile(heartRates)), 4, 2);
    calculationsTableModel.setValueAt(pressures.isEmpty() ? "-" : formatDouble(Statistics.calculateFourthQuartile(pressures)), 4, 3);

    calculationsTable.revalidate();
    calculationsTable.repaint();
}

    private String formatDouble(double value) {
        return DECIMAL_FORMAT.format(value);
    }

    private String formatValue(HealthIndicator indicator, List<? extends HealthIndicator> indicators, int index) {
        if (indicator.isIsCritical()) {
            if (index == 0 || !indicators.get(index - 1).isIsCritical()) {
                return DECIMAL_FORMAT.format(indicator.getValue()) + " (" + indicator.getTime().format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")) + ")";
            } else {
                return DECIMAL_FORMAT.format(indicator.getValue());
            }
        } else {
            if (index > 0 && indicators.get(index - 1).isIsCritical()) {
                for (int i = index - 1; i >= 0; i--) {
                    if (i == 0) {
                        Duration duration = Duration.between(indicators.get(i).getTime(), indicator.getTime());
                        return DECIMAL_FORMAT.format(indicator.getValue()) + " (" + formatDuration(duration) + ")";
                    }
                    HealthIndicator prevIndicator = indicators.get(i - 1);
                    if (!prevIndicator.isIsCritical()) {
                        Duration duration = Duration.between(indicators.get(i).getTime(), indicator.getTime());
                        return DECIMAL_FORMAT.format(indicator.getValue()) + " (" + formatDuration(duration) + ")";
                    }
                }
            }
            return DECIMAL_FORMAT.format(indicator.getValue());
        }
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    private void checkAndNotify(List<Temperature> temperatures, List<HeartRate> heartRates, List<CentralVenousPressure> pressures) {
        StringBuilder criticalConditions = new StringBuilder();

        if (isLastCritical(temperatures)) {
            criticalConditions.append("Температура вышла в критическое состояние.\n");
        }
        if (isLastCritical(heartRates)) {
            criticalConditions.append("Сердечный ритм вышел в критическое состояние.\n");
        }
        if (isLastCritical(pressures)) {
            criticalConditions.append("Центральное венозное давление вышло в критическое состояние.\n");
        }

        if (criticalConditions.length() > 0) {
            if (previousDialog != null && previousDialog.isShowing()) {
                previousDialog.dispose();
            }

            JOptionPane optionPane = new JOptionPane(criticalConditions.toString(), JOptionPane.WARNING_MESSAGE);
            previousDialog = optionPane.createDialog("Критическое состояние");
            previousDialog.setVisible(true);
        }
    }

    private boolean isLastCritical(List<? extends HealthIndicator> indicators) {
        if (indicators.size() < 2) {
            return false;
        }
        HealthIndicator last = indicators.get(indicators.size() - 1);
        HealthIndicator previous = indicators.get(indicators.size() - 2);
        return last.isIsCritical() && !previous.isIsCritical();
    }
}
