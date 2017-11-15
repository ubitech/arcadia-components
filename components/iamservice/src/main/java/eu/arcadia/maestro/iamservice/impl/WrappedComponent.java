package eu.arcadia.maestro.iamservice.impl;

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
import eu.arcadia.maestro.iamservice.util.IpHandlingUtil;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/3/2017.
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "IAMService",
        componentversion = "0.1.0",
        componentdescription = "The FI-STAR Access Control Service.",
        tags = {"Identity and Access Management", "OAuth2", "XACML", "FI-PPP Enabler"})

/**
 * Arcadia Metrics
 */
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
/*@ArcadiaConfigurationParameter(
        name = "cDeployedLocation",
        description = "Required location to deploy",
        parametertype = ParameterType.String,
        defaultvalue = "[Countries.GERMANY]",
        mutableafterstartup = false)*/

/**
 * Container Parameters
 */
@ArcadiaContainerParameter(
        key = "DockerRegistryUri",
        value = "https://hub.docker.com/",
        description = "Docker registry URI")
@ArcadiaContainerParameter(
        key = "DockerRegistryUserName",
        value = "arcadia",
        description = "Docker registry username")
@ArcadiaContainerParameter(
        key = "DockerRegistryUserPassword",
        value = "!arcadia!",
        description = "Docker Docker registry password")
@ArcadiaContainerParameter(
        key = "DockerImage",
        value = "trantub/fistar_iac",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "7770",
        description = "The port which FI-STAR IAC server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "7770",
        description = "The port which FI-STAR IAC server is listening on the container")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "iamservice", allowsMultipleTenants = true)
public class WrappedComponent {
     /*
    * Arcadia Configuration Parameters
    */
     /*public static String getCDeployedLocation() {
         return System.getProperty("cDeployedLocation");

     }

    public static void setCDeployedLocation(String uri) {
        System.setProperty("cDeployedLocation", uri);

    }*/

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getIamserviceuri() {
        Enumeration<NetworkInterface> n = null;
        InetAddress addr = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();

        }
        catch (SocketException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);

        }

        for (; n.hasMoreElements();) {
            NetworkInterface e = n.nextElement();
            Enumeration<InetAddress> a = e.getInetAddresses();
            for (; a.hasMoreElements();) {
                addr = a.nextElement();
                if ((IpHandlingUtil.isIpV4Address(addr.getHostAddress())) &&
                        (!IpHandlingUtil.isIpV6Address(addr.getHostAddress())) &&
                        (!addr.getHostAddress().toString().equals("127.0.0.1")) &&
                        (!addr.getHostAddress().toString().equals("172.17.0.1"))) {
                    return addr.getHostAddress();

                }

            }

        }

        return null;

    }

    public static String getIamserviceport() {
        return "7770";

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    //None for this component

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static String getMIsRunning() {
        return "";

    }

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "iamservice")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
