package eu.arcadia.maestro.orion.eu.arcadia.maestro.orion.impl;

/**
 * Created by John Tsantilis
 * (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on 1/2/2017.
 */

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.DependencyExport;
import eu.arcadia.annotations.DependencyResolutionHandler;
import eu.arcadia.annotations.ParameterType;

import java.util.logging.Logger;

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(componentname = "OrionBroker", componentversion = "0.1.0", componentdescription = "The Orion Context Broker is an implementation of the Publish/Subscribe Context Broker GE, providing the NGSI9 and NGSI10 interfaces.", tags = {"Context broker", "Publish/Subscribe", "IoT", "smart devices"})
/**
 * Arcadia wrapper exposed Metrics
 */
//@ArcadiaMetric(name = "Bytes_received", description = "Number of bytes received", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)
//@ArcadiaMetric(name = "Bytes_sent", description = "Number of bytes sent", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)
//@ArcadiaMetric(name = "Connections", description = "Number of current connection to mysql server", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "10000", minvalue = "0", higherisbetter = false)
/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(name = "ImplementationClassName", description = "The class name of the API implementation", parametertype = ParameterType.String, defaultvalue = "OrionBrokerMetricsProvider", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "DockerImage", description = "Docker image name", parametertype = ParameterType.String, defaultvalue = "fiware/orion", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "DockerExpose", description = "Docker expose port", parametertype = ParameterType.String, defaultvalue = "1026", mutableafterstartup = false)
/**
 * Arcadia Dependency Exports
 */
@DependencyExport(CEPCID = "oriontcp", allowsMultipleTenants = true)
public class WrappedComponent {
    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

    /*
     * Arcadia wrapper Configuration Parameters
     *
     */
    public String getImplementationClassName() {
        return "";

    }

    public String getDockerImage() {
        return "";

    }

    public String getDockerExpose() {
        return "";

    }

    public String getDockerEnvironment() {
        return "";

    }

    public String getSystemProperties() {
        return "";

    }

    /*
     * Arcadia wrapper exposed Metrics
     *
     */
    public static int getBytes_received() {
        return 0;

    }

    public static int getBytes_sent() {
        return 0;

    }

    public static int getConnections() {
        return 0;

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @DependencyResolutionHandler(CEPCID = "oriontcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));
    }

}
