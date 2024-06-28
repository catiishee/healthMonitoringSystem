/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health;

import java.time.LocalDateTime;

/**
 *Класс представляет показатель температуры пациента.
 * Этот класс наследует класс {@link HealthIndicator}, который включает значение показателя,
 * информацию о критическом состоянии и временную метку.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class Temperature extends HealthIndicator {

    /**
     * Конструктор для создания нового показателя температуры с текущей временной меткой.
     * 
     * @param value Значение температуры.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     */
    public Temperature(double value, boolean isCritical) {
        super(value, isCritical);
    }

    /**
     * Конструктор для создания нового показателя температуры с заданной временной меткой.
     * 
     * @param value Значение температуры.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     * @param time Временная метка, указывающая, когда было зафиксировано значение показателя.
     */
    public Temperature(double value, boolean isCritical, LocalDateTime time) {
        super(value, isCritical, time);
    }

}
