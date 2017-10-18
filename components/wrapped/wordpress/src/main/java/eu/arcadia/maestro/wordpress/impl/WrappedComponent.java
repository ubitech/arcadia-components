package eu.arcadia.maestro.wordpress.impl;

/**
 * Created by John Tsantilis (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi)
 * on 10/2/2017.
 */
import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;

import java.util.logging.Logger;

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "Wordpress",
        componentversion = "0.1.0",
        componentdescription = "WordPress is a free and open source blogging tool and a content "
        + "management system (CMS) based on PHP and MySQL, which runs on a web hosting service.",
        tags = {"CMS", "site", "PHP", "MySQL"})

/**
 * Arcadia Metrics
 */
//Non for this component

/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(
        name = "db_user",
        description = "The username of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "WORDPRESS_DB_USER=root",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_password",
        description = "The password of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "WORDPRESS_DB_PASSWORD=!arcadia!",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_host",
        description = "The hostname where mysql server can be reached",
        parametertype = ParameterType.String,
        defaultvalue = "WORDPRESS_DB_HOST=%%", //Old: %WORDPRESS_DB_HOST%
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
        value = "wordpress",
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
@ArcadiaChainableEndpoint(CEPCID = "wordpresstcp", allowsMultipleTenants = true)
public class WrappedComponent {
    /*
    * Arcadia Configuration Parameters
    *
    */
    public String getDb_user() {
        return "";

    }

    public String getDb_password() {
        return "";

    }

    public String getDb_host() {
        return "";

    }

    //==================================================================================================================
    //==================================================================================================================
    /**
     * Find the public IP of the VM
     *
     * @return The public IP of the VM
     */
    public static String getUri() {
        return System.getProperty("uri");

    }

    public static String getPort() {
        return System.getProperty("port");

    }

    //==================================================================================================================
    //==================================================================================================================
    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mysqltcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        String environment = System.getProperty("environment");
        System.setProperty("environment", environment.replace(
                "%WORDPRESS_DB_HOST%",
                getUri() + ":" + getPort()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
