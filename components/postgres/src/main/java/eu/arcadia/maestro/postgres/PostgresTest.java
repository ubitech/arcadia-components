package eu.arcadia.maestro.postgres;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.postgres.impl.MySQLMetricsProvider;
import eu.arcadia.maestro.postgres.util.Metric;

import java.util.Arrays;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
public class PostgresTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new WordpressMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
