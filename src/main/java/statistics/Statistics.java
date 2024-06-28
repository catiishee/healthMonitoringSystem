/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package statistics;

import health.HealthIndicator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Класс Statistics предоставляет статические методы для расчета различных статистических показателей
 * для списка объектов HealthIndicator.
 * 
 * Методы включают вычисление среднего значения, дисперсии, первого и четвертого квартилей.
 * Эти методы могут быть использованы для анализа медицинских показателей пациентов.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 */
public class Statistics {

    /**
     * Вычисляет среднее значение для списка объектов HealthIndicator.
     * 
     * @param indicators Список объектов HealthIndicator.
     * @return Среднее значение или 0.0, если список пуст.
     */
    public static double calculateMean(List<? extends HealthIndicator> indicators) {
        return indicators.stream()
                .mapToDouble(HealthIndicator::getValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Вычисляет дисперсию для списка объектов HealthIndicator.
     * 
     * Дисперсия рассчитывается как среднее квадратичное отклонение от среднего значения.
     * 
     * @param indicators Список объектов HealthIndicator.
     * @return Дисперсия или 0.0, если список пуст.
     */
    public static double calculateVariance(List<? extends HealthIndicator> indicators) {
        double mean = calculateMean(indicators);
        return indicators.stream()
                .mapToDouble(indicator -> Math.pow(indicator.getValue() - mean, 2))
                .average()
                .orElse(0.0);
    }

    /**
     * Вычисляет первый квартиль для списка объектов HealthIndicator.
     * 
     * Первый квартиль - это значение, ниже которого находится 25% наблюдений.
     * 
     * @param indicators Список объектов HealthIndicator.
     * @return Значение первого квартиля.
     */
    public static double calculateFirstQuartile(List<? extends HealthIndicator> indicators) {
        List<HealthIndicator> sortedIndicators = indicators.stream()
                .sorted(Comparator.comparingDouble(HealthIndicator::getValue))
                .collect(Collectors.toList());

        int quartileIndex = sortedIndicators.size() / 4;
        return sortedIndicators.get(quartileIndex).getValue();
    }

    /**
     * Вычисляет четвертый квартиль для списка объектов HealthIndicator.
     * 
     * Четвертый квартиль - это значение, ниже которого находится 75% наблюдений.
     * 
     * @param indicators Список объектов HealthIndicator.
     * @return Значение четвертого квартиля.
     */
    public static double calculateFourthQuartile(List<? extends HealthIndicator> indicators) {
        List<HealthIndicator> sortedIndicators = indicators.stream()
                .sorted(Comparator.comparingDouble(HealthIndicator::getValue))
                .collect(Collectors.toList());

        int quartileIndex = (sortedIndicators.size() * 3) / 4;
        return sortedIndicators.get(quartileIndex).getValue();
    }

}
