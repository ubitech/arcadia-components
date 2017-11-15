package eu.arcadia.maestro.uihealth.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;
import eu.arcadia.maestro.uihealth.util.IpHandlingUtil;

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
@ArcadiaComponent(
        componentname = "UIHealth",
        componentversion = "0.1.0",
        componentdescription = "Web User Interface for the ARCADIA RPM scenario.",
        tags = {"User Interface", "Heart Rate"})

/**
 * Arcadia Metrics
 */
@ArcadiaMetric(
        name = "mIsRunning",
        description = "Status of Service",
        unitofmeasurement = "string",
        valuetype = ValueType.String,
        maxvalue = "",
        minvalue = "",
        higherisbetter = false)


/**
 * Arcadia Configuration Parameters
 */
/*@ArcadiaConfigurationParameter(
        name = "cDeployedLocation",
        description = "Required location to deploy",
        parametertype = ParameterType.String,
        defaultvalue = "DE",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "cClientID",
        description = "Application ID",
        parametertype = ParameterType.String,
        defaultvalue = "OdBSR3CoPtwEKIedDUE5j_WU3rEa",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "cClientSecret",
        description = "Application Secret",
        parametertype = ParameterType.String,
        defaultvalue = "SJiaVnwV16qYoMiuEvJyxDcH5HMa",
        mutableafterstartup = false)*/
@ArcadiaConfigurationParameter(
        name = "apigateway_endpoint",
        description = "The endpoint of the API Gateway Service",
        parametertype = ParameterType.String,
        defaultvalue = "ENDPOINT=apigatewayuri:apigatewayport",
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
        value = "trantub/arcadia_ui",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "7770",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "7770",
        description = "The port which mysql server is listening on the container")

/**
 * Miscellaneous
 */
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
//Non for this component
public class WrappedComponent {
     /*
    * Arcadia Configuration Parameters
    */
     /*public static String getCDeployedLocation() {
         return System.getProperty("cDeployedLocation");

     }

    public static void setCDeployedLocation(String uri) {
        System.setProperty("cDeployedLocation", uri);

    }

    public static String getCClientID() {
        return System.getProperty("cClientID");

    }

    public static void setCClientID(String str) {
        System.setProperty("cClientID", str);

    }

    public static String getCClientSecret() {
        return System.getProperty("cClientSecret");

    }

    public static void setCClientSecret(String str) {
        System.setProperty("cClientSecret", str);

    }*/

     public String getApigateway_endpoint() {
         return "";

     }

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    //Non for this component

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getApigatewayuri() {
        return System.getProperty("apigatewayuri");

    }

    public static String getApigatewayport() {
        return System.getProperty("apigatewayport");

    }

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static String getMIsRunning() {
        return String.valueOf(isrunning);

    }

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "apigateway")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());
    private static Integer isrunning = 0;

}
