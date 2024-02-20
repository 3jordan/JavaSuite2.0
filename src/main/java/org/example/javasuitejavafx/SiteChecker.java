package org.example.javasuitejavafx;
import java.net.HttpURLConnection;
import java.net.URL;

public class SiteChecker {

    private static boolean checkSite(URL site) {
        try {
            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.getContent();
            return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String isSiteUp(String link) {
        try {
            // Check if the URL has a protocol specified, if not, prepend "http://"
            if (!link.matches("^[a-zA-Z]+://.*")) {
                link = "http://" + link;
            }

            // Validate the URL
            URL url = new URL(link);
            System.out.println("Checking URL: " + url);

            // Perform site connectivity check
            boolean isUp = checkSite(url);

            return isUp ? "Website is Online" : "Website is Offline";
        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid URL";
        }
    }
}

