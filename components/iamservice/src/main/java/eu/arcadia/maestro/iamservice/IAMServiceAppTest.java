package eu.arcadia.maestro.iamservice;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.iamservice.impl.IAMServiceAppMetricsProvider;
import eu.arcadia.maestro.iamservice.util.Metric;

import java.util.Arrays;

/**
 * Created by John Tsantilis
 * (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on 1/2/2017.
 */

public class IAMServiceAppTest {
    public static void main(String[] args) {
        //Instantiate
        MetricsProvider metricsProvider = new IAMServiceAppMetricsProvider();

        Arrays.asList(Metric.values()).forEach(metric -> {
            System.out.println(String.format("Metric: %s\tValue: %s", metric, metricsProvider.getMetric(metric.name())));

        });

    }

}
