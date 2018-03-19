package eu.arcadia.maestro.traefikapigateway.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.maestro.traefikapigateway.util.IpHandlingUtil;

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
        componentname = "TraefikApigateway",
        componentversion = "0.1.0",
        componentdescription = "Træfik (pronounced like traffic) is a modern HTTP reverse proxy and load balancer " +
                "made to deploy microservices with ease.",
        tags = {"proxy", "HTTP reverse proxy", "microservices"})

/**
 * Arcadia Metrics
 */
//None for this component


/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(
        name = "consul_http_token",
        description = "The http token used for consul ACL",
        parametertype = ParameterType.String,
        defaultvalue = "CONSUL_HTTP_TOKEN=4f3fa35a-h3ab-45a2-914f-f82a34fab0c45",
        mutableafterstartup = false)

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
        value = "traefik",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "80,8080",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "80,8080",
        description = "The port which mysql server is listening on the container")
@ArcadiaContainerParameter(
        key = "DockerCmd",
        value = "--consul --consul.endpoint=195.46.17.227:8500",
        description = "Docker added command")

/**
 * Miscellaneous
 */
//No ArcadiaBehavioralProfile
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "traefikapigateway", allowsMultipleTenants = true)
public class WrappedComponent {
     /*
    * Arcadia Configuration Parameters
    */
     public String getConsul_http_token() {
         return "";

     }

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getTraefikapigatewayuri() {
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

    public static String getTraefikapigatewayport() {
        return System.getProperty("DockerHostExposedPorts");

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getApigatewayuri() {
        return System.getProperty("apigatewayuri");

    }

    public static String getApigatewayport() {
        return System.getProperty("apigatewayport");

    }

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    //None for this component

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "apigateway")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "traefikapigateway")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
