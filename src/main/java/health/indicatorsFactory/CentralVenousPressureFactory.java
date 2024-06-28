/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.CentralVenousPressure;
import health.HealthIndicator;

/**
 * Класс, представляющий фабрику для создания объектов типа {@link CentralVenousPressure}.
 * Этот класс наследует {@link HealthIndicatorFactory} и предоставляет конкретную реализацию
 * для создания и оценки показателей центрального венозного давления.
 *
 * @author Kate Shcherbinina
 */
public class CentralVenousPressureFactory extends HealthIndicatorFactory<CentralVenousPressure> {

    /**
     * Конструктор, создающий фабрику с предопределенными параметрами для центрального венозного давления.
     * Устанавливает критические и нормальные границы значений, а также шаги для изменения значений.
     */
    public CentralVenousPressureFactory() {
        super(0, 20, 2, 12, 0.5, 2);
    }

    /**
     * Создает новый объект {@link CentralVenousPressure} на основе переданного объекта {@link HealthIndicator}.
     * Генерирует новое значение и определяет, является ли оно критическим.
     * 
     * @param healthIndicator объект {@link HealthIndicator}, на основе которого создается новый показатель.
     * @return новый объект {@link CentralVenousPressure} с сгенерированным значением и критическим состоянием.
     */
    @Override
    public CentralVenousPressure createIndicator(HealthIndicator healthIndicator) {
        double newValue = newValue(healthIndicator);
        boolean isCritical = newValue <= getMinNormal() || newValue >= getMaxNormal();
        return new CentralVenousPressure(newValue, isCritical);
    }

}
