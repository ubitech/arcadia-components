package eu.arcadia.maestro.sracomponent;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.sracomponent.impl.SraAppMetricsProvider;
import eu.arcadia.maestro.sracomponent.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/3/2017.
 */

public class SraAppTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new SraAppMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}