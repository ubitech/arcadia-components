package eu.arcadia.maestro.dummycomponentfive;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.dummycomponentfive.impl.DummyComponentFiveAppMetricsProvider;
import eu.arcadia.maestro.dummycomponentfive.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/3/2017.
 */

public class DummyComponentFiveAppTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new DummyComponentFiveAppMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
