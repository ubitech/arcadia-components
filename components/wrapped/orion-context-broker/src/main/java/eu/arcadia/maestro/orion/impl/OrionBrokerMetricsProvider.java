package eu.arcadia.maestro.orion.impl;

import eu.arcadia.maestro.api.MetricsProvider;
import eu.arcadia.maestro.orion.util.DSHandler;

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
            String ip = System.getenv("%MONGO_DB_HOST%");
            System.out.printf("%n%n%n%n%nMONGO_DB_HOST: %s%n%n%n%n%n%n", ip);
            metric = DSHandler.INSTANCE.sendGet(name, ip);

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
