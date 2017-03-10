package eu.arcadia.maestro.dummycomponentsix.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
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
@ArcadiaComponent(componentname = "DummyComponentSix",
        componentversion = "0.1.0",
        componentdescription = "A dummy component created for Arcadia demonstration purposes.",
        tags = {"Arcadia", "component", "Arcadia Pilot", "alpine", "DummyComponentSix"})

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
        value = "alpine",
        description = "Docker image name")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "dummycomponentsix", allowsMultipleTenants = true)
public class WrappedComponent {
    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "dummycomponentsix")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}