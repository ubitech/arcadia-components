package eu.arcadia.maestro.phpdashboard.impl;

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
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/2/2017.
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(componentname = "PhpDashboard",
        componentversion = "0.1.0",
        componentdescription = "A PHP pilot application created for Arcadia demonstration purposes.",
        tags = {"PHP", "Application", "Arcadia Pilot"})

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
        value = "giannis20012001/phpdashboard",
        description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose",
        value = "80",
        description = "Docker expose port")
@ArcadiaContainerParameter(key = "DockerEnvironment",
        value = "SHARE_HOST=%SHARE_HOST%," +
                "GIT_USER=itsantilis," +
                "GIT_PASS=arcadialtfe," +
                "DB_HOST=%DB_HOST%," +
                "DB_ROOT_USER=root," +
                "DB_ROOT_PASS=gen6," +
                "DB_NAME=gen6," +
                "DB_USER=gen6," +
                "DB_PASS=gen6",
        description = "Docker environment variables")
@ArcadiaContainerParameter(key = "DockerPrivilegeflag",
value = "true",
description = "Enable docker privileged condition")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mysqltcp", allowsMultipleTenants = true)
@ArcadiaChainableEndpoint(CEPCID = "samba", allowsMultipleTenants = true)
public class WrappedComponent {
    public static String getUri() {
        return System.getProperty("uri");

    }

    public static String getPort() {
        return System.getProperty("port");

    }

    public static String getSambauri() {
        return System.getProperty("sambauri");

    }

    public static String getSambaport() {
        return System.getProperty("sambaport");

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    //TODO: Add secondary ArcadiaChainableEndpointBindingHandler to support additional component interconnection
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mysqltcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        String environment = System.getProperty("environment");
        System.setProperty("environment", environment.replace("%DB_HOST%", getUri())
                .replace("%SHARE_HOST%", getSambauri()));
        LOGGER.info(String.format("env: %s", System.getProperty("environment")));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "samba")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}