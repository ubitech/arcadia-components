package eu.arcadia.maestro.orion.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;

import java.util.logging.Logger;

/**
 * Created by John Tsantilis (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on
 * 1/2/2017.
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(componentname = "OrionBroker", componentversion = "0.1.0", componentdescription = "The Orion Context Broker is an implementation of the Publish/Subscribe Context Broker GE, providing the NGSI9 and NGSI10 interfaces.", tags = {"Context broker", "Publish/Subscribe", "IoT", "smart devices"})

/**
 * Arcadia wrapper exposed Metrics
 */
/*@ArcadiaMetric(name = "metrics",
        description = "A multi-level JSON tree response that includes various internal metrics",
        unitofmeasurement = "string",
        valuetype = ValueType.String,
        maxvalue = "N/A",
        minvalue = "N/A",
        higherisbetter = false)*/

/**
 * Arcadia Configuration Parameters
 */
//None for this component

/**
 * Docker Container Parameters
 */
@ArcadiaContainerParameter(key = "DockerImage", value = "fiware/orion", description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", value = "1026", description = "Docker expose port")
@ArcadiaContainerParameter(key = "DockerCmd", value = "-dbhost %MONGO_DB_HOST%", description = "Docker added command")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mongotcp", allowsMultipleTenants = true)
public class WrappedComponent {
    /*
     * Arcadia wrapper exposed Metrics
     *
     */
    public static String getMetrics() {
        return "";

    }

    public static String getOrionUri() {
        return System.getProperty("orionUri");

    }

    public static String getOrionPort() {
        return System.getProperty("orionPort");

    }

    public static String getUri() {
        return System.getProperty("uri");

    }

    public static String getPort() {
        return System.getProperty("port");

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mongotcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        String CMD = System.getProperty("cmd");
        System.setProperty("cmd", System.getProperty("cmd").replace("%MONGO_DB_HOST%", getUri()));
        LOGGER.info(String.format("cmd: %s", System.getProperty("cmd")));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}

