package eu.arcadia.maestro.phpdashboard.impl;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/2/2017.
 */

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
/*@ArcadiaContainerParameter(key = "DockerImage",
        value = "fiware/orion",
        description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose",
        value = "1026",
        description = "Docker expose port")
@ArcadiaContainerParameter(key = "DockerCmd",
        value = "-dbhost %MONGO_DB_HOST%",
        description = "Docker added command")*/

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "phpdashboard", allowsMultipleTenants = true)
public class WrappedComponent {
    public static String getPhpDashboardUri() {
        return System.getProperty("phpDashboardUri");

    }

    public static String getPhpDashboardPort() {
        return System.getProperty("phpDashboardPort");

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    //TODO: Add secondary ArcadiaChainableEndpointBindingHandler to support additional component interconnection
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mysqltcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        /*String CMD = System.getProperty("cmd");
        System.setProperty("cmd", System.getProperty("cmd").replace("%MONGO_DB_HOST%", getUri()));*/
        LOGGER.info(String.format("cmd: %s", System.getProperty("cmd")));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
