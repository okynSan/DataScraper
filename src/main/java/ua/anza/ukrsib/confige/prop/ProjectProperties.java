/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.confige.prop;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class ProjectProperties {

    private static Properties properties;

    static {
        properties = new Properties();
        URL url = ClassLoader.getSystemResource("Configuration.properties");
        try {
            properties.load(url.openStream());
        } catch (IOException ex) {
        }
    }

    public static String getPropertyByKey(String key) {
        return properties.getProperty(key);
    }

}
