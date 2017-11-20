package eu.arcadia.maestro.turnserver.impl;

import eu.arcadia.maestro.api.MetricsProvider;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
public class TurnServerMetricsProvider implements MetricsProvider {
    @Override
    public <T> T getMetric(String s) {
        return null;

    }

    @Override
    public <T> T getMetric(String s, Class<T> aClass) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}
