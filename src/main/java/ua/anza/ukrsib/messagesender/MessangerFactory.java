/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.messagesender;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import ua.anza.ukrsib.messagesender.telegram.TelegramMessanger;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class MessangerFactory {

    private static Properties properties;

    static {
        properties = new Properties();
        URL url = ClassLoader.getSystemResource("Configuration.properties");
        try {
            properties.load(url.openStream());
        } catch (IOException ex) {
        }
    }

    public static AbstractMessanger getMessenger(MessangerEnum messangerEnum) {
        switch (messangerEnum) {
            case Telegram:
                return new TelegramMessanger(properties.getProperty("url"),
                        properties.getProperty("apiToken"),
                        properties.getProperty("chatId")
                );
            default:
                new TelegramMessanger(null, null, null);
        }
        return null;

    }

}
