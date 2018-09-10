/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.messagesender.telegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.log4j.Logger;
import ua.anza.ukrsib.messagesender.AbstractMessanger;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class TelegramMessanger extends AbstractMessanger {

    final static Logger messangerLogger = Logger.getLogger("messenger");

    public TelegramMessanger(String url, String apiToken, String chatId) {
        super(url, apiToken, chatId);
    }

    @Override
    public void sendMessage(String message) {
        URL domain;
        try {
            domain = new URL(this.URL_STRING);

            URL url = new URL(domain + this.API_TOKEN + this.CHAT_ID + message);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            messangerLogger.info(response);
            throw new MalformedURLException("test");
        } catch (MalformedURLException ex) {
            messangerLogger.warn(ex);
        } catch (IOException ex) {
            messangerLogger.warn(ex);
        }
    }

}
