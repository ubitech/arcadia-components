package eu.arcadia.maestro.turnserver;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.turnserver.impl.TurnServerMetricsProvider;
import eu.arcadia.maestro.turnserver.util.Metric;

import java.util.Arrays;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
public class TurnServerTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new TurnServerMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
