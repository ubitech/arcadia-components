package eu.arcadia.maestro.signalingHandler.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ScaleBehavior;

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
        componentname = "SignalingHandler",
        componentversion = "0.1.0",
        componentdescription = "A SignalingHandler component created for Arcadia pilot purposes.",
        tags = {"Arcadia", "component", "Arcadia Pilot", "SignalingHandler"})

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
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "signalinghandler", allowsMultipleTenants = true)
public class WrappedComponent {
    /*
    * Arcadia Configuration Parameters
    *
    */
    //None for this component

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    //None for this component

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getPostgresuri() {
        return System.getProperty("postgresuri");

    }

    public static String getPostgresport() {
        return System.getProperty("postgresport");

    }

    public static String getRedisuri() {
        return System.getProperty("redisuri");

    }

    public static String getRedisport() {
        return System.getProperty("redisport");

    }

    public static String getTurnserveruri() {
        return System.getProperty("turnserveruri");

    }

    public static String getTurnserverport() {
        return System.getProperty("turnserverport");

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
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "postgres")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "redis")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "turnserver")
    public static void bindDependencyTertiary(ChainingInfo chainingInfo) {
        //

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
