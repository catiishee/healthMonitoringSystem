/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health;

import java.time.LocalDateTime;

/**
 * Класс представляет показатель центрального венозного давления пациента.
 * Этот класс наследует от {@link HealthIndicator} и используется для хранения значения центрального венозного давления, информации о критическом состоянии и временной метки.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class CentralVenousPressure extends HealthIndicator {

    /**
     * Конструктор для создания нового показателя центрального венозного давления с текущей временной меткой.
     * 
     * @param value Значение центрального венозного давления.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     */
    public CentralVenousPressure(double value, boolean isCritical) {
        super(value, isCritical);
    }

    /**
     * Конструктор для создания нового показателя центрального венозного давления с заданной временной меткой.
     * 
     * @param value Значение центрального венозного давления.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     * @param time Временная метка, указывающая, когда было зафиксировано значение показателя.
     */
    public CentralVenousPressure(double value, boolean isCritical, LocalDateTime time) {
        super(value, isCritical, time);
    }

}
