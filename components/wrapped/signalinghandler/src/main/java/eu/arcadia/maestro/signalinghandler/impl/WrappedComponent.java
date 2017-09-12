package eu.arcadia.maestro.signalinghandler.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.maestro.signalinghandler.util.IpHandlingUtil;


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
@ArcadiaComponent(componentname = "SignalingHandler",
        componentversion = "0.1.0",
        componentdescription = "A Signaling Handler component created for Arcadia.",
        tags = {"Arcadia", "component", "Arcadia Pilot", "SignalingHandler"})

/**
 * Arcadia wrapper exposed Metrics
 */
//Non for this component

/**
 * Arcadia Configuration Parameters
 */
//None for this component

/**
 * Docker Container Parameters
 */
@ArcadiaContainerParameter(key = "DockerImage",
        value = "httpd:2.4",
        description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose",
        value = "80",
        description = "Docker expose port")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "signalinghandler", allowsMultipleTenants = true)
@SuppressWarnings("Duplicates")
public class WrappedComponent {
    public static String getSignalinghandlerruri() {
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

    public static String getSignalinghandlerport() {
        return "80";

    }

    public static String getPersistenceengineuri() {
        return System.getProperty("persistenceengineuri");

    }

    public static String getPersistenceengineport() {
        return System.getProperty("persistenceengineport");

    }

    public static String getCallhandleruri() {
        return System.getProperty("callhandleruri");

    }

    public static String getCallhandlerport() {
        return System.getProperty("callhandlerport");

    }

    public static String getPushserveruri() {
        return System.getProperty("pushserveruri");

    }

    public static String getPushserverport() {
        return System.getProperty("pushserverport");

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "persistenceengine")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "callhandler")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }


    @ArcadiaChainableEndpointBindingHandler(CEPCID = "pushserver")
    public static void bindDependencyTertiary(ChainingInfo chainingInfo) {
        //

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
