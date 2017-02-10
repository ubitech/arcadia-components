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
import eu.arcadia.annotations.ArcadiaChainableEndpointBindingHandler;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
/**
 * Arcadia Component Definition
 *
 */
@ArcadiaComponent(componentname = "MySQL", componentversion = "0.1.0", componentdescription = "MySQL is a widely used, open-source relational database management system (RDBMS)", tags = {"database", "rdbms", "server", "sql"})
/**
 * Arcadia Metrics
 */
@ArcadiaMetric(name = "Bytes_received", description = "Number of bytes received", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "Bytes_sent", description = "Number of bytes sent", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)
@ArcadiaMetric(name = "Connections", description = "Number of current connection to mysql server", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "10000", minvalue = "0", higherisbetter = false)
/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(name = "db_user", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "root", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_password", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "!arcadia!", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_port", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "db_host", description = "System Properties", parametertype = ParameterType.String, defaultvalue = "localhost", mutableafterstartup = false)

/**
 * Container Parameters
 */
@ArcadiaContainerParameter(key = "DockerImage", value = "mysql", description = "Docker image name")
@ArcadiaContainerParameter(key = "DockerExpose", value = "3306", description = "Docker expose port")
@ArcadiaContainerParameter(key = "DockerEnvironment", value = "MYSQL_ROOT_PASSWORD=!arcadia!,MYSQL_ROOT_HOST=%", description = "Docker environment variables")

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

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

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

    public String getDb_port() {
        return "";
    }

    public String getDb_host() {
        return "";
    }

    public static String getUri() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            try {
                return in.readLine(); //you get the IP as a String
            } catch (IOException ex) {
                Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WrappedComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static String getPort() {
        return System.getProperty("db_port");
    }

    /*
     * Arcadia Metrics 
     * 
     */
    public static int getBytes_received() {
        return 0;
    }

    public static int getBytes_sent() {
        return 0;
    }

    public static int getConnections() {
        return 0;
    }

    /**
     * Handle the binding
     *
     * @param chainingInfo ChainingInfo object
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "mysqltcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));
    }

}
