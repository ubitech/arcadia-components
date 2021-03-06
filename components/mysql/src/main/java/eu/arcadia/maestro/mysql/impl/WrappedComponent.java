package eu.arcadia.maestro.mysql.impl;

import eu.arcadia.annotations.ArcadiaContainerParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;
import java.util.logging.Logger;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.maestro.mysql.util.IpHandlingUtil;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */

/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(
        componentname = "MySQL",
        componentversion = "1.0.0",
        componentdescription = "MySQL is a widely used, open-source relational database management system (RDBMS)",
        tags = {"database", "rdbms", "server", "sql"})

/**
 * Arcadia Metrics
 */
@ArcadiaMetric(
        name = "Bytes_received",
        description = "Number of bytes received",
        unitofmeasurement = "Number of bytes",
        valuetype = ValueType.Integer,
        maxvalue = "100000",
        minvalue = "0",
        higherisbetter = false)
@ArcadiaMetric(
        name = "Bytes_sent",
        description = "Number of bytes sent",
        unitofmeasurement = "Number of bytes",
        valuetype = ValueType.Integer,
        maxvalue = "100000",
        minvalue = "0",
        higherisbetter = false)
@ArcadiaMetric(
        name = "Connections",
        description = "Number of current connections to mysql server",
        unitofmeasurement = "Number of connections",
        valuetype = ValueType.Integer,
        maxvalue = "10000",
        minvalue = "0",
        higherisbetter = false)

/**
 * Arcadia Configuration Parameters
 */
/*@ArcadiaConfigurationParameter(name = "db_user", description = "The username of the database", parametertype = ParameterType.String, defaultvalue = "root", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_password", description = "The password of the database user", parametertype = ParameterType.String, defaultvalue = "!arcadia!", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_port", description = "The port which mysql server is listening", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_host", description = "The hostname which the mysql server can be reached", parametertype = ParameterType.String, defaultvalue = "localhost", mutableafterstartup = false)*/
//
@ArcadiaConfigurationParameter(
        name = "db_user",
        description = "The username of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "MYSQL_USER=root",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_password",
        description = "The password of the database user",
        parametertype = ParameterType.String,
        defaultvalue = "MYSQL_ROOT_PASSWORD=!arcadia!",
        mutableafterstartup = false)
@ArcadiaConfigurationParameter(
        name = "db_host",
        description = "The hostname where mysql server can be reached",
        parametertype = ParameterType.String,
        defaultvalue = "MYSQL_ROOT_HOST=%",
        mutableafterstartup = false)

/**
 * Container Parameters
 */
/*@ArcadiaContainerParameter(key = "DockerEnvironment", value = "MYSQL_ROOT_PASSWORD=!arcadia!,MYSQL_ROOT_HOST=%", description = "Docker environment variables")*/
@ArcadiaContainerParameter(
        key = "DockerRegistryUri",
        value = "https://hub.docker.com/",
        description = "Docker registry URI")
@ArcadiaContainerParameter(
        key = "DockerRegistryUserName",
        value = "arcadia",
        description = "Docker registry username")
@ArcadiaContainerParameter
        (key = "DockerRegistryUserPassword",
        value = "!arcadia!",
        description = "Docker Docker registry password")
@ArcadiaContainerParameter(
        key = "DockerImage",
        value = "giannis20012001/arcadia-mysql",
        description = "Docker image name")
@ArcadiaContainerParameter(
        key = "DockerHostExposedPorts",
        value = "3306",
        description = "The port which mysql server is listening on the host")
@ArcadiaContainerParameter(
        key = "DockerContainerExposedPorts",
        value = "3306",
        description = "The port which mysql server is listening on the container")

/**
 * Miscellaneous
 */
//No ArcadiaBehavioralProfile
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)

/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mysqltcp", allowsMultipleTenants = true)
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
    //Parameters shared to other components
    //==================================================================================================================
    @SuppressWarnings("Duplicates")
    public static String getMysqluri() {
        Enumeration<NetworkInterface> n = null;
        InetAddress addr = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();

        } catch (SocketException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);

        }

        for (; n.hasMoreElements();) {
            NetworkInterface e = n.nextElement();
            Enumeration<InetAddress> a = e.getInetAddresses();
            for (; a.hasMoreElements();) {
                addr = a.nextElement();
                if ((IpHandlingUtil.isIpV4Address(addr.getHostAddress()))
                        && (!IpHandlingUtil.isIpV6Address(addr.getHostAddress()))
                        && (!addr.getHostAddress().equals("127.0.0.1"))
                        && (!addr.getHostAddress().equals("172.17.0.1"))) {
                    return addr.getHostAddress();

                }

            }

        }

        return null;

    }

    public static String getMysqlport() {
        return System.getProperty("DockerHostExposedPorts");

    }

    //==================================================================================================================
    //Parameters required by other components
    //==================================================================================================================
    //None for this component

    //==================================================================================================================
    //Component metrics
    //==================================================================================================================
    public static int getBytes_received() {
        return 0;

    }

    public static int getBytes_sent() {
        return 0;

    }

    public static int getConnections() {
        return 0;

    }

    //==================================================================================================================
    //Perform bindings
    //==================================================================================================================
    /**
     * Handle binding dependencies to other components
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "mysqltcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));

    }

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

}
