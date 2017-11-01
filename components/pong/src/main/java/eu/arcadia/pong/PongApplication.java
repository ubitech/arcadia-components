package eu.arcadia.pong;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.arcadia.ArcadiaConstants;
import eu.arcadia.agentglue.GroundedComponentInfo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

/**
 *
 * @author Christos Paraskeva (ch.paraskeva at gmail dot com)
 */
@Configuration
@ComponentScan(basePackages = "eu.arcadia.pong")
@EnableAutoConfiguration
@RestController
public class PongApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = Logger.getLogger(PongApplication.class.getName());
    public static final String DEFAULT_PORT = "8080";
    public static final String INFO_URI = "/getInfo";

    @RequestMapping(value = INFO_URI, method = RequestMethod.GET)
    public String getInfo() {
//        System.setProperty("Component.receivedRequests",String.valueOf(Integer.parseInt(System.getProperty("Component.receivedRequests"))+1));

        String ret = "[--- Component Info ---]\n";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            GroundedComponentInfo groundedComponentInfo = gson.fromJson(new FileReader(ArcadiaConstants.basePathAgent + File.separator + ArcadiaConstants.groundedInfoJsonFn), GroundedComponentInfo.class);
            ret += gson.toJson(groundedComponentInfo);
        } catch (FileNotFoundException e) {
            ret += "Error getting information about Component !";
            LOGGER.severe(e.getMessage());
        }
        return ret;
    }

    @RequestMapping(value = "/pongs", method = RequestMethod.GET)
    public String getPongs() {
        return NativeComponent.getPongs();
    }

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    public void pong() {
        NativeComponent.pong();
    }

    //Just needed to compile the jar. Never gets called.
    public static void main(String[] args) {
    }
}
