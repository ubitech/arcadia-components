package eu.arcadia.maestro.transcodingmaster.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.maestro.transcodingmaster.util.IpHandlingUtil;

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
        componentname = "TranscodingMaster",
        componentversion = "0.1.0",
        componentdescription = "A JPPF Transcoding Master created for Arcadia demonstration purposes.",
        tags = {"Arcadia", "component", "Arcadia Pilot", "alpine", "TranscodingMaster"})

/**
 * Arcadia Metrics
 */
//Non for this component

/**
 * Arcadia Configuration Parameters
 */
//None for this component

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
        value = "httpd:2.4",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "80",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "80",
        description = "The port which mysql server is listening on the container")


/**
 * Miscellaneous
 */
//No ArcadiaBehavioralProfile
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "transcodingmaster", allowsMultipleTenants = true)
public class WrappedComponent {
    /*
    * Arcadia Configuration Parameters
    *
    */
    //None for this component

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getTranscodingmasteruri() {
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

    public static String getTranscodingmasterport() {
        return "";

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getNosqlrepouri() {
        return System.getProperty("nosqlrepouri");

    }

    public static String getNosqlrepoport() {
        return System.getProperty("nosqlrepoport");

    }

    public static String getTrascodingworkeroneuri() {
        return System.getProperty("trascodingworkertwouri");

    }

    public static String getTrascodingworkeroneport() {
        return System.getProperty("trascodingworkeroneport");

    }

    public static String getTrascodingworkertwouri() {
        return System.getProperty("trascodingworkertwouri");

    }

    public static String getTrascodingworkertwoport() {
        return System.getProperty("trascodingworkertwoport");

    }

    public static String getTrascodingworkerthreeuri() {
        return System.getProperty("trascodingworkerthreeuri");

    }

    public static String getTrascodingworkerthreeport() {
        return System.getProperty("trascodingworkerthreeport");

    }

    public static String getTrascodingworkerfoururi() {
        return System.getProperty("trascodingworkerfoururi");

    }

    public static String getTrascodingworkerfourport() {
        return System.getProperty("trascodingworkerfourport");

    }

    public static String getTrascodingworkerfiveuri() {
        return System.getProperty("trascodingworkerfiveuri");

    }

    public static String getTrascodingworkerfiveport() {
        return System.getProperty("trascodingworkerfiveport");

    }

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    //Non for this component

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "nosqlrepo")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "trascodingworkerone")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "trascodingworkertwo")
    public static void bindDependencyTertiary(ChainingInfo chainingInfo) {
        //

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "trascodingworkerthree")
    public static void bindDependencyQuaternary(ChainingInfo chainingInfo) {
        //

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "trascodingworkerfour")
    public static void bindDependencyQuinary(ChainingInfo chainingInfo) {
        //

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "trascodingworkerfive")
    public static void bindDependencySenary(ChainingInfo chainingInfo) {
        //

    }

    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "transcodingmaster")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
