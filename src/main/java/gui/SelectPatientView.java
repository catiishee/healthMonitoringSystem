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
import monitoring.ApplicationController;

/**
 *
 * @author user
 */
public class SelectPatientView extends JFrame {

    private JTextField idField;
    private JButton openButton;
    private ApplicationController controller;

    public SelectPatientView(ApplicationController controller) {
        this.controller = controller;
        setTitle("Открыть пациента");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField(15);
        openButton = new JButton("Открыть");

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
