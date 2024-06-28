/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

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
 *Класс PatientView представляет собой окно для создания нового пациента.
 * 
 * Окно позволяет пользователю вводить идентификатор пациента (ID) и его ФИО,
 * а затем создает нового пациента и открывает окно мониторинга.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class PatientView extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JButton createButton;
    private ApplicationController controller;

    /**
     * Конструктор для создания окна нового пациента.
     * 
     * @param controller Контроллер приложения для управления логикой создания пациента.
     */
    public PatientView(ApplicationController controller) {
        this.controller = controller;
        setTitle("Новый пациент");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idField = new JTextField(15);
        nameField = new JTextField(15);
        createButton = new JButton("Создать");

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

        ((AbstractDocument) nameField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[a-zA-Zа-яА-Я\\s]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[a-zA-Zа-яА-Я\\s]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                if (!id.isEmpty() && !name.isEmpty() && isValidName(name)) {
                    controller.createPatient(id, name);
                    new MonitoringView(controller);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите корректный ID и имя.");
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

    /**
     * Проверяет корректность введенного имени пациента.
     * 
     * Имя должно состоять из трех слов и содержать только буквы и пробелы.
     * 
     * @param name Имя пациента для проверки.
     * @return true, если имя корректно; false в противном случае.
     */
    private boolean isValidName(String name) {
        String[] words = name.split("\\s+");
        return words.length == 3 && name.matches("[a-zA-Zа-яА-Я\\s]+");
    }
}
