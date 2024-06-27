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
 *
 * @author user
 */
public class Statistics {

    public static double calculateMean(List<? extends HealthIndicator> indicators) {
        return indicators.stream()
                .mapToDouble(HealthIndicator::getValue)
                .average()
                .orElse(0.0);
    }

    public static double calculateVariance(List<? extends HealthIndicator> indicators) {
        double mean = calculateMean(indicators);
        return indicators.stream()
                .mapToDouble(indicator -> Math.pow(indicator.getValue() - mean, 2))
                .average()
                .orElse(0.0);
    }

    public static double calculateFirstQuartile(List<? extends HealthIndicator> indicators) {
        List<HealthIndicator> sortedIndicators = indicators.stream()
                .sorted(Comparator.comparingDouble(HealthIndicator::getValue))
                .collect(Collectors.toList());

        int quartileIndex = sortedIndicators.size() / 4;
        return sortedIndicators.get(quartileIndex).getValue();
    }

    public static double calculateFourthQuartile(List<? extends HealthIndicator> indicators) {
        List<HealthIndicator> sortedIndicators = indicators.stream()
                .sorted(Comparator.comparingDouble(HealthIndicator::getValue))
                .collect(Collectors.toList());

        int quartileIndex = (sortedIndicators.size() * 3) / 4;
        return sortedIndicators.get(quartileIndex).getValue();
    }

}
