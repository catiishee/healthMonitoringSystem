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
import java.util.*;
import java.util.stream.*;

/**
 *
 * @author user
 */
public class PatientSerializer {

    private static final String DIRECTORY = "src/main/resources/logs";

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
                    line.append(temp.getValue()).append(";").append(temp.isIsCritical() ? "critical" : "normal").append(";");
                } else {
                    line.append(";;");
                }

                if (i < patient.getHeartRates().size()) {
                    HeartRate rate = patient.getHeartRates().get(i);
                    line.append(rate.getValue()).append(";").append(rate.isIsCritical() ? "critical" : "normal").append(";");
                } else {
                    line.append(";;");
                }

                if (i < patient.getPressures().size()) {
                    CentralVenousPressure pressure = patient.getPressures().get(i);
                    line.append(pressure.getValue()).append(";").append(pressure.isIsCritical() ? "critical" : "normal").append(";");
                } else {
                    line.append(";;");
                }

                writer.write(line.toString() + "\n");
            }
        }
    }

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

                                if (data.length >= 2 && !data[0].isEmpty()) {
                                    patient.addTemperature(new Temperature(
                                            Double.parseDouble(data[0]),
                                            "critical".equals(data[1])
                                    ));
                                }

                                if (data.length >= 4 && !data[2].isEmpty()) {
                                    patient.addHeartRate(new HeartRate(
                                            Double.parseDouble(data[2]),
                                            "critical".equals(data[3])
                                    ));
                                }

                                if (data.length >= 6 && !data[4].isEmpty()) {
                                    patient.addPressure(new CentralVenousPressure(
                                            Double.parseDouble(data[4]),
                                            "critical".equals(data[5])
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
