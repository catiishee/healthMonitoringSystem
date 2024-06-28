/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package health.indicatorsFactory;

import health.HealthIndicator;
import java.util.Random;

/**
 * Абстрактная фабрика для создания объектов типа {@link HealthIndicator} и их производных.
 * Класс предоставляет основные параметры для создания и генерации новых значений показателей здоровья,
 * таких как минимальные и максимальные значения для нормальных и критических состояний, а также шаги для изменения значений.
 * 
 * @author Kate Shcherbinina
 * @version 1.0
 * @param <T> Тип показателя здоровья, который наследует {@link HealthIndicator}.
 */
public abstract class HealthIndicatorFactory<T extends HealthIndicator> {

    private double minCritical;
    private double maxCritical;
    private double minNormal;
    private double maxNormal;
    private double minStep;
    private double maxStep;

    /**
     * Конструктор для инициализации фабрики с заданными параметрами.
     *
     * @param minCritical Минимальное значение для критического состояния.
     * @param maxCritical Максимальное значение для критического состояния.
     * @param minNormal Минимальное значение для нормального состояния.
     * @param maxNormal Максимальное значение для нормального состояния.
     * @param minStep Минимальный шаг для изменения значения.
     * @param maxStep Максимальный шаг для изменения значения.
     */
    public HealthIndicatorFactory(double minCritical, double maxCritical, double minNormal, double maxNormal, double minStep, double maxStep) {
        this.minCritical = minCritical;
        this.maxCritical = maxCritical;
        this.minNormal = minNormal;
        this.maxNormal = maxNormal;
        this.minStep = minStep;
        this.maxStep = maxStep;
    }

    /**
     * Абстрактный метод для создания индикатора на основе переданного объекта {@link HealthIndicator}.
     * 
     * @param healthIndicator объект {@link HealthIndicator}, на основе которого создается новый индикатор.
     * @return новый объект типа {@link HealthIndicator}, созданный на основе переданного объекта.
     */
    public abstract T createIndicator(HealthIndicator healthIndicator);

    /**
     * Метод для создания индикатора с использованием среднего значения между минимальным и максимальным нормальными значениями.
     * 
     * @return новый объект типа {@link HealthIndicator} с начальным значением среднего между минимальным и максимальным нормальными значениями.
     */
    public T createIndicator() {
        double mean = (minNormal + maxNormal) / 2;
        return createIndicator(new HealthIndicator(mean, false));
    }

    /**
     * Возвращает минимальное значение для критического состояния.
     * 
     * @return минимальное значение для критического состояния.
     */
    public double getMinCritical() {
        return minCritical;
    }

    /**
     * Возвращает максимальное значение для критического состояния.
     * 
     * @return максимальное значение для критического состояния.
     */
    public double getMaxCritical() {
        return maxCritical;
    }

    /**
     * Возвращает минимальное значение для нормального состояния.
     * 
     * @return минимальное значение для нормального состояния.
     */
    public double getMinNormal() {
        return minNormal;
    }

    /**
     * Возвращает максимальное значение для нормального состояния.
     * 
     * @return максимальное значение для нормального состояния.
     */
    public double getMaxNormal() {
        return maxNormal;
    }

    /**
     * Возвращает минимальный шаг для изменения значения.
     * 
     * @return минимальный шаг для изменения значения.
     */
    public double getMinStep() {
        return minStep;
    }

    /**
     * Возвращает максимальный шаг для изменения значения.
     * 
     * @return максимальный шаг для изменения значения.
     */
    public double getMaxStep() {
        return maxStep;
    }

    /**
     * Генерирует новое значение для показателя здоровья на основе текущего значения.
     * Новое значение рассчитывается случайным образом с добавлением или вычитанием случайного шага.
     * Значение корректируется в пределах заданных критических границ.
     * 
     * @param healthIndicator текущий объект {@link HealthIndicator}, для которого генерируется новое значение.
     * @return новое значение для показателя здоровья.
     */
    protected double newValue(HealthIndicator healthIndicator) {
        Random random = new Random();
        double currentValue = healthIndicator.getValue();
        double step = getMinStep() + (getMaxStep() - getMinStep()) * random.nextDouble();
        boolean addStep = random.nextBoolean();

        double newValue;
        if (addStep) {
            newValue = currentValue + step;
        } else {
            newValue = currentValue - step;
        }

        if (newValue < getMinCritical()) {
            newValue = getMinCritical();
        } else if (newValue > getMaxCritical()) {
            newValue = getMaxCritical();
        }
        return newValue;
    }
}
