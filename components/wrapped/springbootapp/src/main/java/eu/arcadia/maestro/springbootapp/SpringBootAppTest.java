package eu.arcadia.maestro.springbootapp;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.springbootapp.impl.SpringBootAppMetricsProvider;
import eu.arcadia.maestro.springbootapp.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/3/2017.
 */

public class SpringBootAppTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new SpringBootAppMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
