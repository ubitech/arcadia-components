package eu.arcadia.maestro.phpdashboard.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;

import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/2/2017.
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "PhpDashboard",
        componentversion = "0.1.0",
        componentdescription = "A PHP pilot application created for Arcadia demonstration purposes.",
        tags = {"PHP", "Application", "Arcadia Pilot"})

/**
 * Arcadia Metrics
 */
@ArcadiaMetric(
        name = "Apache_Active_Workers",
        description = "Number of active Apache workers",
        unitofmeasurement = "Number of Apache workers",
        valuetype = ValueType.Integer,
        maxvalue = "100000",
        minvalue = "0",
        higherisbetter = false)

/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(
        name = "share_host",
        description = "The samba server",
        parametertype = ParameterType.String,
        defaultvalue = "SHARE_HOST=sambauri", //Old: SHARE_HOST=%SHARE_HOST%
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "git_user",
        description = "The git user to fetch app updates from repo",
        parametertype = ParameterType.String,
        defaultvalue = "GIT_USER=gen6", //Old: GIT_USER=itsantilis
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "git_pass",
        description = "The git user password",
        parametertype = ParameterType.String,
        defaultvalue = "GIT_PASS=gen6ltfe", //Old: GIT_PASS=arcadialtfe
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_host",
        description = "The hostname where mysql server can be reached",
        parametertype = ParameterType.String,
        defaultvalue = "DB_HOST=mysqluri", //Old: DB_HOST=%DB_HOST%
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_root",
        description = "The username of the root database user",
        parametertype = ParameterType.String,
        defaultvalue = "DB_ROOT_USER=root", //Old: DB_ROOT_USER=root
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_root_password",
        description = "The password of root database user",
        parametertype = ParameterType.String,
        defaultvalue = "DB_ROOT_PASS=gen6", //Old: DB_ROOT_PASS=gen6
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_name",
        description = "The database name",
        parametertype = ParameterType.String,
        defaultvalue = "DB_NAME=gen6", //Old: DB_NAME=gen6
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_user",
        description = "The username of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "DB_USER=gen6", //Old: DB_USER=gen6
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_password",
        description = "The password of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "DB_PASS=gen6", //Old: DB_PASS=gen6
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
        value = "giannis20012001/phpdashboard",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "80",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "80",
        description = "The port which mysql server is listening on the container")
@ArcadiaContainerParameter(
        key = "DockerPrivilegeflag",
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
@ArcadiaChainableEndpoint(CEPCID = "phpdashboard", allowsMultipleTenants = true)
public class WrappedComponent {
    /*
    * Arcadia Configuration Parameters
    *
    */
    public String getShare_host() {
        return "";

    }

    public String getGit_user() {
        return "";

    }

    public String getGit_pass() {
        return "";

    }

    public String getDb_host() {
        return "";

    }

    public String getDb_root() {
        return "";

    }

    public String getDb_root_password() {
        return "";

    }

    public String getDb_name() {
        return "";

    }

    public String getDb_user() {
        return "";

    }

    public String getDb_password() {
        return "";

    }

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    //None for this component

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getMysqluri() {
        return System.getProperty("mysqluri");

    }

    public static String getMysqlport() {
        return System.getProperty("mysqlport");

    }

    public static String getSambauri() {
        return System.getProperty("sambauri");

    }

    public static String getSambaport() {
        return System.getProperty("sambaport");

    }

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static int getApache_Active_Workers() {
        return 0;

    }

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "mysqltcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "samba")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}