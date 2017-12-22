package eu.arcadia.maestro.apigateway.impl;

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
import eu.arcadia.maestro.apigateway.util.IpHandlingUtil;

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
        componentname = "APIGateway",
        componentversion = "0.1.0",
        componentdescription = "API Gateway using Netflix Zuul.",
        tags = {"API Gateway", "Application Firewall"})

/**
 * Arcadia Metrics
 */
@ArcadiaMetric(
        name = "isRunning",
        description = "Status of Service",
        unitofmeasurement = "string",
        valuetype = ValueType.String,
        maxvalue = "",
        minvalue = "",
        higherisbetter = false)
@ArcadiaMetric(name =
        "unauthorizeRequestRate",
        description = "Unauthorized Request Rate",
        unitofmeasurement = "min",
        valuetype = ValueType.Integer,
        maxvalue = "",
        minvalue = "0",
        higherisbetter = false)
/*@ArcadiaMetric(
        name = "errorRequestRate",
        description = "Request Rate",
        unitofmeasurement = "min",
        valuetype = ValueType.Integer,
        maxvalue = "",
        minvalue = "0",
        higherisbetter = false)*/


/**
 * Arcadia Configuration Parameters
 */
/*@ArcadiaConfigurationParameter(
        name = "cRequiredOAuth",
        description = "Whether an OAuth2 token is required for the access",
        parametertype = ParameterType.String,
        defaultvalue = "0",
        mutableafterstartup = true)
@ArcadiaConfigurationParameter(
        name = "cUnauthorizedRRDelay",
        description = "Number of seconds an unauthorized request source address need " +
                "to wait for its next request to be served.",
        parametertype = ParameterType.String,
        defaultvalue = "0",
        mutableafterstartup = true)
@ArcadiaConfigurationParameter(
        name = "cErrorRRDelay",
        description = "Number of seconds an error request source address need to " +
                "wait for its next request to be served.",
        parametertype = ParameterType.String,
        defaultvalue = "0",
        mutableafterstartup = true)
@ArcadiaConfigurationParameter(
        name = "cDeployedLocation",
        description = "Required location to deploy",
        parametertype = ParameterType.String,
        defaultvalue = "Countries.GERMANY",
        mutableafterstartup = false)*/
@ArcadiaConfigurationParameter(
        name = "orion_endpoint",
        description = "The endpoint of the orion broker",
        parametertype = ParameterType.String,
        defaultvalue = "ENDPOINT=orionuri:orionport",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "iamservice_endpoint",
        description = "The endpoint of the IAM Service",
        parametertype = ParameterType.String,
        defaultvalue = "IAM=iamserviceuri:iamserviceport",
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
        value = "giannis20012001/apigateway", //OLD: value = "trantub/arcadia_af",
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
@ArcadiaChainableEndpoint(CEPCID = "apigateway", allowsMultipleTenants = true)
public class WrappedComponent {
     /*
    * Arcadia Configuration Parameters
    */
     /*public static String getCRequiredOAuth() {
         return System.getProperty("cRequiredOAuth");

     }

    public static void setCRequiredOAuth(String val) {
        System.setProperty("cRequiredOAuth", val);

    }

    public static String getCUnauthorizedRRDelay() {
        return "0";

    }

    public static void setCUnauthorizedRRDelay(String val) {
        //

    }

    public static int getCErrorRRDelay() {
        return 0;

    }

    public static void setCErrorRRDelay(String val) {
        //

    }

    public static String getCDeployedLocation() {
        return System.getProperty("cDeployedLocation");

    }

    public static void setCDeployedLocation(String uri) {
        System.setProperty("cDeployedLocation", uri);

    }*/

    public String getOrion_endpoint() {
        return "";

    }

    public String getIamservice_endpoint() {
        return "";

    }

    //==================================================================================================================
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getApigatewayuri() {
        Enumeration<NetworkInterface> n = null;
        InetAddress addr = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();

        }
        catch (SocketException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);

        }

        for (; n.hasMoreElements();) {
            NetworkInterface e = n.nextElement();
            Enumeration<InetAddress> a = e.getInetAddresses();
            for (; a.hasMoreElements();) {
                addr = a.nextElement();
                if ((IpHandlingUtil.isIpV4Address(addr.getHostAddress())) &&
                        (!IpHandlingUtil.isIpV6Address(addr.getHostAddress())) &&
                        (!addr.getHostAddress().toString().equals("127.0.0.1")) &&
                        (!addr.getHostAddress().toString().equals("172.17.0.1"))) {
                    return addr.getHostAddress();

                }

            }

        }

        return null;

    }

    public static String getApigatewayport() {
        return "7770";

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    public static String getIamserviceuri() {
        return System.getProperty("iamserviceuri");

    }

    public static String getIamserviceport() {
        return System.getProperty("iamserviceport");

    }

    public static String getOrionuri() {
        return System.getProperty("orionuri");

    }

    public static String getOrionport() {
        return System.getProperty("orionport");

    }

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static String getIsRunning() {
        return "";

    }

    public static String getUnauthorizeRequestRate() {
        return "";

    }

    /*public static String getErrorRequestRate() {
        return "";

    }*/

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies by other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointBindingHandler(CEPCID = "oriontcp")
    public static void bindDependency(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    @ArcadiaChainableEndpointBindingHandler(CEPCID = "iamservice")
    public static void bindDependencySecondary(ChainingInfo chainingInfo) {
        //

    }

    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "apigateway")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
