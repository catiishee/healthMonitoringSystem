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
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import monitoring.ApplicationController;

/**
 * Класс SelectPatientView представляет собой окно для открытия существующего пациента по его идентификатору (ID).
 * 
 * Окно позволяет пользователю вводить идентификатор пациента и открывает окно мониторинга, если пациент найден.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class SelectPatientView extends JFrame {

    private JTextField idField;
    private JButton openButton;
    private ApplicationController controller;

    /**
     * Конструктор для создания окна выбора пациента.
     * 
     * @param controller Контроллер приложения для управления логикой открытия пациента.
     */
    public SelectPatientView(ApplicationController controller) {
        this.controller = controller;
        setTitle("Открыть пациента");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField(15);
        openButton = new JButton("Открыть");

        ((AbstractDocument) idField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    Patient patient = controller.loadPatient(id);
                    if (patient != null) {
                        new MonitoringView(controller);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Пациент не найден.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите ID пациента.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("ID пациента:"));
        panel.add(idField);
        panel.add(openButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}