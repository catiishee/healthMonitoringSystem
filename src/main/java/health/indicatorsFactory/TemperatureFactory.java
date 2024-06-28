/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import health.Temperature;

/**
 * Класс, представляющий фабрику для создания объектов типа {@link Temperature}.
 * Этот класс наследует {@link HealthIndicatorFactory} и предоставляет конкретную реализацию
 * для создания и оценки показателей температуры.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class TemperatureFactory extends HealthIndicatorFactory<Temperature> {

    /**
     * Конструктор, создающий фабрику с предопределенными параметрами для температуры.
     * Устанавливает критические и нормальные границы значений, а также шаги для изменения значений.
     */
    public TemperatureFactory() {
        super(35.0, 40.0, 35.5, 37.0, 0.1, 0.5);
    }

    /**
     * Создает новый объект {@link Temperature} на основе переданного объекта {@link HealthIndicator}.
     * Генерирует новое значение и определяет, является ли оно критическим.
     * 
     * @param healthIndicator объект {@link HealthIndicator}, на основе которого создается новый показатель.
     * @return новый объект {@link Temperature} с сгенерированным значением и критическим состоянием.
     */
    @Override
    public Temperature createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinNormal() || newValue >= getMaxNormal();
        return new Temperature(newValue, isCritical);
    }
     
}
