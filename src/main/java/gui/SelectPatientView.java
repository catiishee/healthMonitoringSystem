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
        setTitle("Open Patient");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField(15);
        openButton = new JButton("Open");

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    Patient patient = loadPatient(id); 
                    if (patient != null) {
                        new MonitoringView(controller);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a patient ID.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Patient ID:"));
        panel.add(idField);
        panel.add(openButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private Patient loadPatient(String id) {
        
        return null; 
    }
}
