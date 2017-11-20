package eu.arcadia.maestro.turnserver.impl;

/**
 * Created by John Tsantilis (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi)
 * on 10/2/2017.
 */

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.maestro.turnserver.util.IpHandlingUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "TurnServer",
        componentversion = "0.1.0",
        componentdescription = "A TurnServer component created for Arcadia pilot purposes.",
        tags = {"Arcadia", "component", "Arcadia Pilot", "TurnServer"})

/**
 * Arcadia Metrics
 */
//Non for this component

/**
 * Arcadia Configuration Parameters
 */
//Non for this component

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
        value = "giannis20012001/turnserver",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "443",
        description = "The port which postgres server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "443",
        description = "The port which postgres server is listening on the container")
@ArcadiaContainerParameter(
        key = "DockerCmd",
        value = "/usr/bin/turnserver -c /etc/turnserver.conf -v",
        description = "Docker added command")


/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "turnserver", allowsMultipleTenants = true)
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
    public static String getTurnserveruri() {
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

    public static String getTurnserverport() {
        return System.getProperty("DockerHostExposedPorts");

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    //Non for this component

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    //Non for this component

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "turnserver")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
