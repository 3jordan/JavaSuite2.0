package org.example.javasuitejavafx;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;


public class SiteChecker {

    private static boolean checkSite(URL site) {
        try {
            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.getContent();
            return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (SocketTimeoutException tout) {
            return false;
        } catch (IOException ioex) {
            // You may decide on more specific behaviour...
            return false;
        }
    }

    public static String isSiteUp(String link) {
        try {
            URL url = new URL(link);
            return checkSite(url) ? "Website is Online" : "Website is Offline";

        } catch (Exception e) {
            System.err.println("Invalid Url");
            return "";
        }
    }
}
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            System.out.println("Enter url here");
            System.out.println(SiteChecker.isSiteUp(scanner.nextLine()));

        } catch (Exception e) {
            System.err.println("Invalid Url");
        }

    }
}
