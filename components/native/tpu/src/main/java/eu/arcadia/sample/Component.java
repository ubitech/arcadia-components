package eu.arcadia.sample;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ValueType;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Panagiotis on 26/5/2016.
 */
@ArcadiaComponent(componentname = "tpuv001", componentversion = "0.0.1", componentdescription = "Aditess Transcoding Processing Unit", tags = {"transcoder"})
@ArcadiaMetric(name = "handledRequests", description = "Number of requests handled", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100", minvalue = "0", higherisbetter = false)
@ArcadiaConfigurationParameter(name = "maxrequests", description = "Max allowed request handlings", parametertype = ParameterType.String, defaultvalue = "10", mutableafterstartup = false)
@DependencyExport(CEPCID = "transcodingprocessor", allowsMultipleTenants = true)
@RestController
public class Component {

    private static final Logger logger = Logger.getLogger(Component.class.getName());
    public static ConfigurableApplicationContext appContext;

    public int requests = 0;
    public String maxrequest = "0";

    //Rest Logic
    @RequestMapping(value = "/handlerequest", method = RequestMethod.GET)
    public String handlerequest() {
        handleSynchRequest();
        return "Handled Request: " + getHandledReqs();
    }//EoM    

    private synchronized void handleSynchRequest() {
        requests++;
        System.setProperty("requests", "" + requests);
    }//

    private synchronized String getHandledReqs() {
        return System.getProperty("requests");
    }//

    /*
    ArcadiaConfigurationParameter setter/getter for maxrequests
     */
    public static String getMaxrequests() {
        return System.getProperty("maxrequests");
    }

    public static void setMaxrequests(String maxreq) {
        System.setProperty("maxrequests", maxreq);
    }

    /*
    ArcadiaMetrics (getters only)
     */
    public static String getHandledRequests() {
        //application logic 
        return System.getProperty("requests");
    }

    /*
    Component Lifecycle Management
     */
    @LifecycleInitialize
    public static void init() {
        System.setProperty("Component.sentRequests", String.valueOf(0));
        System.setProperty("Component.receivedRequests", String.valueOf(0));
        System.setProperty("app.port", Application.defaultPort.toString());
        logger.info("----INIT END---");
    }

    @LifecycleStart
    public static String start() {
        if (appContext == null) {
            appContext = SpringApplication.run(new Class[]{Application.class, CustomizationBean.class}, new String[]{});
        } else {
            logger.severe("AppContext is not null ! Application is already started !");
        }
        logger.info("----START END---");
        return String.valueOf(appContext.isActive());
    }

    @LifecycleStop
    public static String stop() {
        if (appContext != null) {
            SpringApplication.exit(appContext);
            appContext.close();
            appContext = null;
        } else {
            logger.severe("AppContext is null ! Application has not been started !");
        }
        return String.valueOf((appContext == null));

    }


    /*
    DependencyExport-related methods
     */
    public static String getUri() {
        return Application.getterURI;
    }

    public static String getPort() {
        return System.getProperty("app.port");
    }

    /*
    Handle the bidning
     */
    @DependencyResolutionHandler(CEPCID = "transcodingprocessor")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        logger.info("BINDED COMPONENT:" + chainingInfo.toString());
    }

}
