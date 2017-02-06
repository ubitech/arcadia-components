package eu.arcadia.maestro.orion.eu.arcadia.maestro.orion.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
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
@ArcadiaMetric(name = "incomingTransactions", description = "Number of requests consumed by Orion", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "incomingTransactionRequestSize", description = "Total size (bytes) in requests associated to incoming transactions", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "incomingTransactionResponseSize", description = "Total size (bytes) in responses associated to incoming transactions", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "incomingTransactionErrors", description = "Number of incoming transactions resulting in error", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "serviceTime", description = "Average time to serve a transaction", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "outgoingTransactions", description = "Number of requests sent by Orion", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "outgoingTransactionRequestSize", description = "Total size (bytes) in requests associated to outgoing transactions", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "outgoingTransactionResponseSize", description = "Total size (bytes) in responses associated to outgoing transactions", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "outgoingTransactionErrors", description = "Number of outgoing transactions resulting in error", unitofmeasurement = "string", valuetype = ValueType.String, maxvalue = "100000", minvalue = "0", higherisbetter = false)
/**
 * Arcadia Configuration Parameters
 */
//None for this component

//BROKER_DATABASE_HOST

/**
 * Docker Container Parameters
 */
@ArcadiaContainerParameter(key = "DockerImage", value = "fiware/orion", description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", value = "1026", description = "Docker expose port")
//@ArcadiaContainerParameter(key = "DockerCmd", value = "-dbhost 172.17.0.1", description = "Docker added command")
@ArcadiaContainerParameter(key = "DockerEnvironment", value = "BROKER_DATABASE_HOST=172.17.0.1", description = "Docker environment variables")

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
     * Arcadia wrapper exposed Metrics
     *
     */
    public static int getIncomingTransactions() {
        return 0;

    }

    public static int getIncomingTransactionRequestSize() {
        return 0;

    }

    public static String getIncomingTransactionResponseSize() {
        return "";

    }

    public static String getIncomingTransactionErrors() {
        return "";

    }

    public static int getServiceTime() {
        return 0;

    }

    public static int getOutgoingTransactions() {
        return 0;

    }

    public static String getOutgoingTransactionRequestSize() {
        return "";

    }

    public static String getOutgoingTransactionResponseSize() {
        return "";

    }

    public static int getOutgoingTransactionErrors() {
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
