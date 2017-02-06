package eu.arcadia.maestro.orion.eu.arcadia.maestro.orion.impl;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.orion.eu.arcadia.maestro.orion.util.DSHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on 1/2/2017.
 */

public class OrionBrokerMetricsProvider implements MetricsProvider {
    @Override
    public String getMetric(String name) {
        String metric = null;

        try {
            metric = DSHandler.INSTANCE.sendGet(name);

        }
        catch (Exception ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);

        }

        return metric;

    }

    @Override
    public <T> T getMetric(String s, Class<T> aClass) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}
