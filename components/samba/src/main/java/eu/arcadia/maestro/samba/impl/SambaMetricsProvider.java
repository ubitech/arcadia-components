package eu.arcadia.maestro.samba.impl;

import eu.arcadia.maestro.api.MetricsProvider;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/2/2017.
 */

public class SambaMetricsProvider implements MetricsProvider {
    @Override
    public <T> T getMetric(String s) {
        return null;

    }

    @Override
    public <T> T getMetric(String s, Class<T> aClass) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}
