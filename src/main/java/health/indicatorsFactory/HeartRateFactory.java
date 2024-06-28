/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import health.HeartRate;

/**
 * Класс, представляющий фабрику для создания объектов типа {@link HeartRate}.
 * Этот класс наследует {@link HealthIndicatorFactory} и предоставляет конкретную реализацию
 * для создания и оценки показателей сердечного ритма.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class HeartRateFactory extends HealthIndicatorFactory<HeartRate> {

    /**
     * Конструктор, создающий фабрику с предопределенными параметрами для сердечного ритма.
     * Устанавливает критические и нормальные границы значений, а также шаги для изменения значений.
     */
    public HeartRateFactory() {
        super(30, 180, 60, 100, 1, 5);
    }

    /**
     * Создает новый объект {@link HeartRate} на основе переданного объекта {@link HealthIndicator}.
     * Генерирует новое значение и определяет, является ли оно критическим.
     * 
     * @param healthIndicator объект {@link HealthIndicator}, на основе которого создается новый показатель.
     * @return новый объект {@link HeartRate} с сгенерированным значением и критическим состоянием.
     */
    @Override
    public HeartRate createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinNormal() || newValue >= getMaxNormal();
        return new HeartRate(newValue, isCritical);
    }

}
