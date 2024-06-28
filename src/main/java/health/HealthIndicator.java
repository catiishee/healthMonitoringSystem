/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health;

import java.time.LocalDateTime;

/**
 * Класс представляет показатель здоровья, содержащий значение, статус критичности и временную метку.
 * Данный класс используется для хранения и управления данными о состоянии здоровья пациента.
 * Он позволяет создавать объекты с текущим временем или заданной временной меткой и предоставляет методы
 * для получения и установки этих значений.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class HealthIndicator {

    private double value;
    private boolean isCritical;
    private LocalDateTime time;

    /**
     * Конструктор для создания нового показателя здоровья с текущим временем.
     * 
     * @param value Значение показателя здоровья.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     */
    public HealthIndicator(double value, boolean isCritical) {
        this.value = value;
        this.isCritical = isCritical;
        this.time = LocalDateTime.now();
    }

    /**
     * Конструктор для создания нового показателя здоровья с заданной временной меткой.
     * 
     * @param value Значение показателя здоровья.
     * @param isCritical Булево значение, указывающее, является ли данный показатель критическим.
     * @param time Временная метка, указывающая, когда было зафиксировано значение показателя.
     */
    public HealthIndicator(double value, boolean isCritical, LocalDateTime time) {
        this.value = value;
        this.isCritical = isCritical;
        this.time = time;
    }

    /**
     * Возвращает временную метку, когда было зафиксировано значение показателя здоровья.
     * 
     * @return Временная метка.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Возвращает значение показателя здоровья.
     * 
     * @return Значение показателя здоровья.
     */
    public double getValue() {
        return value;
    }

    /**
     * Устанавливает новое значение показателя здоровья.
     * 
     * @param value Новое значение показателя здоровья.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Возвращает булево значение, указывающее, является ли данный показатель критическим.
     * 
     * @return Булево значение, указывающее, является ли данный показатель критическим.
     */
    public boolean isIsCritical() {
        return isCritical;
    }

    /**
     * Устанавливает булево значение, указывающее, является ли данный показатель критическим.
     * 
     * @param isCritical Новое булево значение, указывающее, является ли данный показатель критическим.
     */
    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }

}
