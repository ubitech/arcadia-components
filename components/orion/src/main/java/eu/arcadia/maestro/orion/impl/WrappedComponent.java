package eu.arcadia.maestro.orion.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;
import eu.arcadia.maestro.orion.util.IpHandlingUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis (i[dot]tsantilis[at]yahoo.com A.K.A lumi) on
 * 1/2/2017.
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "TUBOrionBroker",
        componentversion = "0.1.0",
        componentdescription = "The Orion Context Broker is an implementation of " +
            "the Publish/Subscribe Context Broker GE, providing the NGSI9 and NGSI10 interfaces.",
        tags = {"Context broker", "Publish/Subscribe", "IoT", "FI-PPP Enabler"})

/**
 * Arcadia wrapper exposed Metrics
 */
@ArcadiaMetric(
        name = "Metrics",
        description = "A multi-level JSON tree response that includes various internal metrics",
        unitofmeasurement = "string",
        valuetype = ValueType.String,
        maxvalue = "N/A",
        minvalue = "N/A",
        higherisbetter = false)

@ArcadiaMetric(
        name = "MIsRunning",
        description = "Status of Service",
        unitofmeasurement = "string",
        valuetype = ValueType.String,
        maxvalue = "",
        minvalue = "",
        higherisbetter = false)

/**
 * Arcadia Configuration Parameters
 */
//None for this component

/**
 * Docker Container Parameters
 */
@ArcadiaContainerParameter(
        key = "DockerRegistryUri",
        value = "https://hub.docker.com/",
        description = "Docker registry URI")
@ArcadiaContainerParameter(
        key = "DockerRegistryUserName",
        value = "arcadia",
        description = "Docker registry username")
@ArcadiaContainerParameter
        (key = "DockerRegistryUserPassword",
                value = "!arcadia!",
                description = "Docker Docker registry password")
@ArcadiaContainerParameter(
        key = "DockerImage",
        value = "trantub/fiware_orion",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "1026",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "1026",
        description = "The port which mysql server is listening on the container")

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
    /*
     * Arcadia Configuration Parameters
     *
     */
    //Non for this component

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getOrionuri() {
        Enumeration<NetworkInterface> n = null;
        InetAddress addr = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();

        } catch (SocketException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);

        }

        for (; n.hasMoreElements();) {
            NetworkInterface e = n.nextElement();
            Enumeration<InetAddress> a = e.getInetAddresses();
            for (; a.hasMoreElements();) {
                addr = a.nextElement();
                if ((IpHandlingUtil.isIpV4Address(addr.getHostAddress()))
                        && (!IpHandlingUtil.isIpV6Address(addr.getHostAddress()))
                        && (!addr.getHostAddress().equals("127.0.0.1"))
                        && (!addr.getHostAddress().equals("172.17.0.1"))) {
                    return addr.getHostAddress();

                }

            }

        }

        return null;

    }

    public static String getOrionport() {
        return System.getProperty("DockerHostExposedPorts");

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    //Non for this component

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static String getMetrics() {
        return "";

    }

    public static String getMIsRunning() {
        return "";

    }

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "oriontcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}

