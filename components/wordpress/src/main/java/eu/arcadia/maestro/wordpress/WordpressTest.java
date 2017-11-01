package eu.arcadia.maestro.wordpress;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.wordpress.impl.WordpressMetricsProvider;
import eu.arcadia.maestro.wordpress.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/2/2017.
 */

public class WordpressTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new WordpressMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
