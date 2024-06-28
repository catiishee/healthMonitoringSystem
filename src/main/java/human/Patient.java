/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package human;

import health.CentralVenousPressure;
import health.HeartRate;
import health.Temperature;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Класс представляет пациента с его измерениями температуры, сердечного ритма и центрального венозного давления.
 * Класс позволяет добавлять новые измерения, получать списки последних измерений и предоставлять методы
 * для работы с критическими состояниями.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class Patient {

    String id;
    String fullname;
    // Деки для хранения последних 10 измерений температуры, сердечного ритма и центрального венозного давления
    Deque<Temperature> temperatures = new ArrayDeque<>();
    Deque<HeartRate> heartRates = new ArrayDeque<>();
    Deque<CentralVenousPressure> pressures = new ArrayDeque<>(); 
    // Максимальный размер хранилища измерений
    private static final int MAX_SIZE = 10;
    
    /**
     * Конструктор для создания объекта пациента с заданным ID и полным именем.
     * 
     * @param id Идентификатор пациента.
     * @param fullname Полное имя пациента.
     */
    public Patient(String id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    /**
     * Возвращает идентификатор пациента.
     * 
     * @return Идентификатор пациента.
     */
    public String getId() {
        return id;
    }

    /**
     * Возвращает полное имя пациента.
     * 
     * @return Полное имя пациента.
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Возвращает список последних измерений температуры.
     * 
     * @return Список измерений температуры.
     */
    public List<Temperature> getTemperatures() {
        return new ArrayList<>(temperatures);
    }

    /**
     * Возвращает список последних измерений сердечного ритма.
     * 
     * @return Список измерений сердечного ритма.
     */
    public List<HeartRate> getHeartRates() {
        return new ArrayList<>(heartRates);
    }

    /**
     * Возвращает список последних измерений центрального венозного давления.
     * 
     * @return Список измерений центрального венозного давления.
     */
    public List<CentralVenousPressure> getPressures() {
        return new ArrayList<>(pressures);
    }

    /**
     * Добавляет новое измерение температуры.
     * Если количество измерений превышает MAX_SIZE, удаляет самое старое измерение.
     * 
     * @param measurement Измерение температуры для добавления.
     */
    public void addTemperature(Temperature measurement) {
        if (temperatures.size() == MAX_SIZE) {
            temperatures.removeFirst();
        }
        temperatures.addLast(measurement);
    }

    /**
     * Добавляет новое измерение сердечного ритма.
     * Если количество измерений превышает MAX_SIZE, удаляет самое старое измерение.
     * 
     * @param measurement Измерение сердечного ритма для добавления.
     */
    public void addHeartRate(HeartRate measurement) {
        if (heartRates.size() == MAX_SIZE) {
            heartRates.removeFirst();
        }
        heartRates.addLast(measurement);
    }

    /**
     * Добавляет новое измерение центрального венозного давления.
     * Если количество измерений превышает MAX_SIZE, удаляет самое старое измерение.
     * 
     * @param measurement Измерение центрального венозного давления для добавления.
     */
    public void addPressure(CentralVenousPressure measurement) {
        if (pressures.size() == MAX_SIZE) {
            pressures.removeFirst();
        }
        pressures.addLast(measurement);
    }

    /**
     * Возвращает последнее измерение температуры.
     * 
     * @return Последнее измерение температуры или null, если измерений нет.
     */
    public Temperature getLastTemperature() {
        if (temperatures.isEmpty()) {
            return null;
        }
        return temperatures.getLast();
    }

    /**
     * Возвращает последнее измерение сердечного ритма.
     * 
     * @return Последнее измерение сердечного ритма или null, если измерений нет.
     */
    public HeartRate getLastHeartRate() {
        if (heartRates.isEmpty()) {
            return null;
        }
        return heartRates.getLast();
    }

    /**
     * Возвращает последнее измерение центрального венозного давления.
     * 
     * @return Последнее измерение центрального венозного давления или null, если измерений нет.
     */
    public CentralVenousPressure getLastPressure() {
        if (pressures.isEmpty()) {
            return null;
        }
        return pressures.getLast();
    }

    /**
     * Возвращает последнее критическое измерение температуры, с которого началось критическое состояние.
     * 
     * @return Последнее критическое измерение температуры или null, если критических измерений нет.
     */
    public Temperature getLastCriticalTemperatureStart() {
        List<Temperature> tempList = new ArrayList<>(temperatures);
        for (int i = tempList.size() - 1; i > 0; i--) {
            if (tempList.get(i).isIsCritical() && !tempList.get(i - 1).isIsCritical()) {
                return tempList.get(i);
            }
        }
        if (tempList.get(0).isIsCritical()) {
            return tempList.get(0);
        }
        return null;
    }

    /**
     * Возвращает последнее критическое измерение сердечного ритма, с которого началось критическое состояние.
     * 
     * @return Последнее критическое измерение сердечного ритма или null, если критических измерений нет.
     */
    public HeartRate getLastCriticalHeartRateStart() {
        List<HeartRate> hrList = new ArrayList<>(heartRates);
        for (int i = hrList.size() - 1; i > 0; i--) {
            if (hrList.get(i).isIsCritical() && !hrList.get(i - 1).isIsCritical()) {
                return hrList.get(i);
            }
        }
        if (hrList.get(0).isIsCritical()) {
            return hrList.get(0);
        }
        return null;
    }

    /**
     * Возвращает последнее критическое измерение центрального венозного давления, с которого началось критическое состояние.
     * 
     * @return Последнее критическое измерение центрального венозного давления или null, если критических измерений нет.
     */
    public CentralVenousPressure getLastCriticalPressureStart() {
        List<CentralVenousPressure> cvpList = new ArrayList<>(pressures);
        for (int i = cvpList.size() - 1; i > 0; i--) {
            if (cvpList.get(i).isIsCritical() && !cvpList.get(i - 1).isIsCritical()) {
                return cvpList.get(i);
            }
        }
        if (cvpList.get(0).isIsCritical()) {
            return cvpList.get(0);
        }
        return null;
    }

}
