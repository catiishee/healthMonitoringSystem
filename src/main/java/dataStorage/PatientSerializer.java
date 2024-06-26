/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStorage;

import health.CentralVenousPressure;
import health.HeartRate;
import health.Temperature;
import human.Patient;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

/**
 * Класс для сериализации и десериализации данных пациентов.
 * Предоставляет методы для сохранения данных пациентов в файлы CSV и загрузки их из файлов.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class PatientSerializer {

    //Домашняя директория текущего пользователя
    private static final String HOME_DIRECTORY = System.getProperty("user.home");
    //Путь к директории, где будут храниться файлы с логами пациентов
    private static final String DIRECTORY = HOME_DIRECTORY + "/patient_logs";
    //Форматтер для преобразования даты и времени в строку и обратно
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Сохраняет данные пациента в файл CSV.
     * 
     * @param patient Пациент, данные которого нужно сохранить.
     * @throws IOException Если возникает ошибка ввода-вывода при сохранении файла.
     */
    public void savePatient(Patient patient) throws IOException {
        if (!Files.exists(Paths.get(DIRECTORY))) {
            Files.createDirectory(Paths.get(DIRECTORY));
        }

        String filename = DIRECTORY + "/" + patient.getId() + ".csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardCharsets.UTF_8)) {
            writer.write(patient.getId() + ";" + patient.getFullname() + "\n");

            int maxSize = Math.max(patient.getTemperatures().size(),
                    Math.max(patient.getHeartRates().size(), patient.getPressures().size()));

            for (int i = 0; i < maxSize; i++) {
                StringBuilder line = new StringBuilder();

                if (i < patient.getTemperatures().size()) {
                    Temperature temp = patient.getTemperatures().get(i);
                    line.append(temp.getValue()).append(";")
                            .append(temp.isIsCritical() ? "critical" : "normal").append(";")
                            .append(DATE_TIME_FORMATTER.format(temp.getTime())).append(";");
                } else {
                    line.append(";;;");
                }

                if (i < patient.getHeartRates().size()) {
                    HeartRate rate = patient.getHeartRates().get(i);
                    line.append(rate.getValue()).append(";")
                            .append(rate.isIsCritical() ? "critical" : "normal").append(";")
                            .append(DATE_TIME_FORMATTER.format(rate.getTime())).append(";");
                } else {
                    line.append(";;;");
                }

                if (i < patient.getPressures().size()) {
                    CentralVenousPressure pressure = patient.getPressures().get(i);
                    line.append(pressure.getValue()).append(";")
                            .append(pressure.isIsCritical() ? "critical" : "normal").append(";")
                            .append(DATE_TIME_FORMATTER.format(pressure.getTime())).append(";");
                } else {
                    line.append(";;;");
                }

                writer.write(line.toString() + "\n");
            }
        }
    }

    /**
     * Загружает данные всех пациентов из файлов CSV в указанной директории.
     * 
     * @return Список пациентов, загруженных из файлов.
     * @throws IOException Если возникает ошибка ввода-вывода при загрузке файлов.
     */
    public List<Patient> loadPatients() throws IOException {
        List<Patient> patients = new ArrayList<>();

        if (!Files.exists(Paths.get(DIRECTORY))) {
            return patients;
        }

        try (Stream<Path> paths = Files.walk(Paths.get(DIRECTORY))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                            String idFullNameLine = reader.readLine();
                            String[] idFullName = idFullNameLine.split(";");
                            Patient patient = new Patient(idFullName[0], idFullName[1]);

                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(";");

                                if (data.length >= 3 && !data[0].isEmpty()) {
                                    LocalDateTime time = LocalDateTime.parse(data[2], DATE_TIME_FORMATTER);
                                    patient.addTemperature(new Temperature(
                                            Double.parseDouble(data[0]),
                                            "critical".equals(data[1]),
                                            time
                                    ));
                                }

                                if (data.length >= 6 && !data[3].isEmpty()) {
                                    LocalDateTime time = LocalDateTime.parse(data[5], DATE_TIME_FORMATTER);
                                    patient.addHeartRate(new HeartRate(
                                            Double.parseDouble(data[3]),
                                            "critical".equals(data[4]),
                                            time
                                    ));
                                }

                                if (data.length >= 9 && !data[6].isEmpty()) {
                                    LocalDateTime time = LocalDateTime.parse(data[8], DATE_TIME_FORMATTER);
                                    patient.addPressure(new CentralVenousPressure(
                                            Double.parseDouble(data[6]),
                                            "critical".equals(data[7]),
                                            time
                                    ));
                                }
                            }

                            patients.add(patient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        return patients;
    }
}