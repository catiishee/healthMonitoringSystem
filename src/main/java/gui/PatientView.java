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
public class PatientView extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JButton createButton;
    private ApplicationController controller;

    public PatientView(ApplicationController controller) {
        this.controller = controller;
        setTitle("Новый пациент");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField(15);
        nameField = new JTextField(15);
        createButton = new JButton("Создать");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                if (!id.isEmpty() && !name.isEmpty()) {
                    controller.createPatient(id, name);
                    new MonitoringView(controller);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите ID и имя.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("ID пациента:"));
        panel.add(idField);
        panel.add(new JLabel("ФИО пациента:"));
        panel.add(nameField);
        panel.add(createButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}
