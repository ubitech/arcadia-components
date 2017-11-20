package eu.arcadia.maestro.postgres.impl;

import eu.arcadia.maestro.api.MetricsProvider;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
public class PostgresMetricsProvider implements MetricsProvider {
    @Override
    public <T> T getMetric(String s) {
        return null;

    }

    @Override
    public <T> T getMetric(String s, Class<T> aClass) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}
