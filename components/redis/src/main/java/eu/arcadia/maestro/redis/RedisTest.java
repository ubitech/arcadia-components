package eu.arcadia.maestro.redis;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.redis.impl.RedisMetricsProvider;
import eu.arcadia.maestro.redis.util.Metric;

import java.util.Arrays;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
public class RedisTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new RedisMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
