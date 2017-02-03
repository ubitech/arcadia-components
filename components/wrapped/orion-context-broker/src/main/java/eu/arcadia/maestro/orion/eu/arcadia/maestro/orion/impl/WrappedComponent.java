package eu.arcadia.maestro.orion.eu.arcadia.maestro.orion.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;

import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on 1/2/2017.
 */

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
//@ArcadiaConfigurationParameter(name = "db_user", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "root", mutableafterstartup = false)
//@ArcadiaConfigurationParameter(name = "db_password", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "!arcadia!", mutableafterstartup = false)
//@ArcadiaConfigurationParameter(name = "db_port", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_host", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "172.17.0.1", mutableafterstartup = false)

/**
 * Docker Container Parameters
 */
@ArcadiaContainerParameter(key = "DockerImage", value = "fiware/orion", description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", value = "1026", description = "Docker expose port")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "oriontcp", allowsMultipleTenants = true)
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
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "oriontcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));
    }

}
