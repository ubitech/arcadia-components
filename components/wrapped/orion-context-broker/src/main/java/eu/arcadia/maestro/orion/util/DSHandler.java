package eu.arcadia.maestro.orion.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 1/2/2017.
 */

public enum DSHandler {
    INSTANCE;

    /**
     * HTTP GET request
     *
     * @param metricName The metric name required to perform teh GET request.
     * @return The HTTP reply (String)
     */
    public String sendGet(String metricName) throws Exception {
        HttpClient client = new DefaultHttpClient();
        StringBuilder sb = new StringBuilder();
        sb.append("http://localhost:1026/admin/" + metricName);
        HttpGet request = new HttpGet(sb.toString());
        // Add request header
        request.addHeader("User-Agent", USER_AGENT);
        //Execute GET
        HttpResponse response = client.execute(request);
        Logger.getLogger(DSHandler.class.getName()).severe("Sending 'GET' request to URL : " + sb.toString());
        Logger.getLogger(DSHandler.class.getName()).severe("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);

        }

        System.out.println(result.toString());

        return result.toString();

    }

    /**
     * Default constructor
     * @return DSHandler Object
     */
    DSHandler() {
        //Do nothing

    }

    private final String USER_AGENT = "Mozilla/5.0";

}
