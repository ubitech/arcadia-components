package eu.arcadia.maestro.php;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.php.impl.PhPMetricsProvider;
import eu.arcadia.maestro.php.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 20/2/2017.
 */

public class PhPTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new PhPMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
