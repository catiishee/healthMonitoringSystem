/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import monitoring.ApplicationController;

/**
 *
 * @author user
 */

public class MainView extends JFrame {
    private JButton newPatientButton;
    private JButton openPatientButton;
    private JButton exitButton;
    private ApplicationController controller = new ApplicationController();

    public MainView() {
        setTitle("Patient Monitoring System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        newPatientButton = new JButton("New Patient");
        openPatientButton = new JButton("Open Patient");
        exitButton = new JButton("Exit");

        newPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PatientView(controller);
            }
        });

        openPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectPatientView(controller);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(newPatientButton);
        panel.add(openPatientButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }
}
