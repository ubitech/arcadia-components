package eu.arcadia.maestro.samba.impl;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 20/2/2017.
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
@ArcadiaComponent(componentname = "samba", componentversion = "0.1.0", componentdescription = "Samba is an Open Source/Free Software suite that provides seamless file and print services to SMB/CIFS clients", tags = {"samba", "file service", "SMB/CIFS"})

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
@ArcadiaContainerParameter(key = "DockerImage", value = "php-image-app", description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", value = "80", description = "Docker expose port")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "php", allowsMultipleTenants = true)
public class WrappedComponent {
    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

    public static String getUri() {
        return System.getProperty("uri");

    }

    public static String getPort() {
        return System.getProperty("port");

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "php")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

}