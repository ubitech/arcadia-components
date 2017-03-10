package eu.arcadia.maestro.wordpress.impl;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 10/2/2017.
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
@ArcadiaComponent(componentname = "Wordpress", 
    componentversion = "0.1.0", 
    componentdescription = "WordPress is a free and open source blogging tool and a content " +
            "management system (CMS) based on PHP and MySQL, which runs on a web hosting service.",
    tags = {"CMS", "site", "PHP", "MySQL"})

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
@ArcadiaContainerParameter(key = "DockerImage", 
    value = "wordpress", 
    description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", 
    value = "80",
    description = "Docker expose port")
@ArcadiaContainerParameter(key = "DockerEnvironment", 
    value = "WORDPRESS_DB_HOST=%WORDPRESS_DB_HOST%,WORDPRESS_DB_USER=root,WORDPRESS_DB_PASSWORD=!arcadia!", 
    description = "Docker environment variables")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {
    public static String getUri() {
        return System.getProperty("uri");

    }

    public static String getPort() {
        return System.getProperty("port");

    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mysqltcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        String environment = System.getProperty("environment");
        System.setProperty("environment", environment.replace("%WORDPRESS_DB_HOST%", getUri() + ":" + getPort()));

    }

 private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
