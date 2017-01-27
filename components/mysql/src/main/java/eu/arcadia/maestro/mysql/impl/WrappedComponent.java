package eu.arcadia.maestro.mysql.impl;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.DependencyExport;
import eu.arcadia.annotations.DependencyResolutionHandler;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ValueType;

import java.util.logging.Logger;

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
@ArcadiaConfigurationParameter(name = "ImplementationClassName", description = "The class name of the API implementation", parametertype = ParameterType.String, defaultvalue = "MySQLMetricsProvider", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "DockerImage", description = "Docker image name", parametertype = ParameterType.String, defaultvalue = "mysql", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "DockerExpose", description = "Docker expose port", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "DockerEnvironment", description = "Docker environment variables", parametertype = ParameterType.String, defaultvalue = "MYSQL_ROOT_PASSWORD=!arcadia!,MYSQL_ROOT_HOST=%", mutableafterstartup = false)
@ArcadiaConfigurationParameter(name = "SystemProperties", description = "Docker environment variables", parametertype = ParameterType.String, defaultvalue = "db_user=root,db_password=!arcadia!,db_port=3306,db_host=localhost", mutableafterstartup = false)
/**
 * Arcadia Dependency Exports
 */
@DependencyExport(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {

    private static final Logger LOGGER = Logger.getLogger(WrappedComponent.class.getName());

    /*
     * Arcadia Configuration Parameters 
     * 
     */
    public String getImplementationClassName() {
        return "";
    }

    public String getDockerImage() {
        return "";
    }

    public String getDockerExpose() {
        return "";
    }

    public String getDockerEnvironment() {
        return "";
    }

    public String getSystemProperties() {
        return "";
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
    @DependencyResolutionHandler(CEPCID = "mysqltcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.info(String.format("BINDED COMPONENT: %s", chainingInfo.toString()));
    }

}
