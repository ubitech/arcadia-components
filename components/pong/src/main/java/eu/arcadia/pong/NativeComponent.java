package eu.arcadia.pong;

import eu.arcadia.agentglue.ChainingInfo;
import eu.arcadia.annotations.ArcadiaBehavioralProfile;
import eu.arcadia.annotations.ArcadiaChainableEndpoint;
import eu.arcadia.annotations.ArcadiaChainableEndpointResolutionHandler;
import eu.arcadia.annotations.ArcadiaComponent;
import eu.arcadia.annotations.ArcadiaConfigurationParameter;
import eu.arcadia.annotations.ArcadiaExecutionRequirement;
import eu.arcadia.annotations.ArcadiaLifecycleInitialize;
import eu.arcadia.annotations.ArcadiaLifecycleStart;
import eu.arcadia.annotations.ArcadiaLifecycleStop;
import eu.arcadia.annotations.ArcadiaMetric;
import eu.arcadia.annotations.ParameterType;
import eu.arcadia.annotations.ScaleBehavior;
import eu.arcadia.annotations.ValueType;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
@ArcadiaComponent(componentname = "Pong", componentversion = "0.1.0", componentdescription = "Sample arcadia native component which reply to ping requests", tags = {"pong", "sample"}, isNative = true)
@ArcadiaMetric(name = "handledRequests", description = "Number of requests handled", unitofmeasurement = "number of requests", valuetype = ValueType.Integer, maxvalue = "100", minvalue = "0", higherisbetter = false)
@ArcadiaConfigurationParameter(name = "maxrequests", description = "Max allowed request handlings", parametertype = ParameterType.String, defaultvalue = "10", mutableafterstartup = false)
@ArcadiaMetric(name = "pongs", description = "Number of pongs", unitofmeasurement = "Number of pong responses", valuetype = ValueType.Integer, maxvalue = "1000", minvalue = "0", higherisbetter = false)
@ArcadiaChainableEndpoint(CEPCID = "pong", allowsMultipleTenants = true)
@ArcadiaBehavioralProfile(scalability = ScaleBehavior.VERTICAL_HORIZONTAL)
@ArcadiaExecutionRequirement(memory = 128, vcpu = 2)
@RestController
public class NativeComponent {

    private static Integer PONGS = 0;

    private static final Logger LOGGER = Logger.getLogger(NativeComponent.class.getName());
    public static ConfigurableApplicationContext appContext;

    public int requests = 0;
    public String maxrequest = "0";

    public static synchronized void pong() {
        PONGS++;
    }

    //Rest Logic
    @RequestMapping(value = "/handlerequest", method = RequestMethod.GET)
    public String handlerequest() {
        handleSynchRequest();
        return "Handled Request: " + getHandledReqs();
    }

    private synchronized void handleSynchRequest() {
        requests++;
        System.setProperty("requests", "" + requests);
    }

    private synchronized String getHandledReqs() {
        return System.getProperty("requests");
    }

    /*
     * Arcadia Configuration Parameters 
     * 
     */
    public static String getMaxrequests() {
        return System.getProperty("maxrequests");
    }

    public static void setMaxrequests(String maxreq) {
        System.setProperty("maxrequests", maxreq);
    }

    /*
     * Arcadia Metrics 
     * 
     */
    public static String getHandledRequests() {
        return System.getProperty("requests");
    }

    public static String getPongs() {
        return String.valueOf(PONGS);
    }

    /*
     * Component Lifecycle Management Methods
     * 
     */
    @ArcadiaLifecycleInitialize
    public static void init() {
        System.setProperty("Component.sentRequests", String.valueOf(0));
        System.setProperty("Component.receivedRequests", String.valueOf(0));
        System.setProperty("app.port", PongApplication.DEFAULT_PORT);
        LOGGER.info("----INIT END---");
    }

    @ArcadiaLifecycleStart
    public static String start() {
        if (appContext == null) {
            appContext = SpringApplication.run(new Class[]{PongApplication.class, CustomizationBean.class}, new String[]{});
        } else {
            LOGGER.severe("AppContext is not null ! Application is already started !");
        }
        LOGGER.info("----START END---");
        return String.valueOf(appContext.isActive());
    }

    @ArcadiaLifecycleStop
    public static String stop() {
        if (appContext != null) {
            SpringApplication.exit(appContext);
            appContext.close();
            appContext = null;
        } else {
            LOGGER.severe("AppContext is null ! Application has not been started !");
        }
        return String.valueOf((appContext == null));

    }


    /*
    DependencyExport-related methods
     */
    public static String getPonguri() {
        Enumeration<NetworkInterface> n = null;
        InetAddress addr = null;
        try {
            n = NetworkInterface.getNetworkInterfaces();

        } catch (SocketException ex) {
            LOGGER.severe(ex.getMessage());

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

    public static String getPongport() {
        return "8080";
    }

    /*
    Handle the bidning
     */
    @ArcadiaChainableEndpointResolutionHandler(CEPCID = "pong")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        LOGGER.log(Level.INFO, "BINDED COMPONENT:{0}", chainingInfo.toString());
    }

}
