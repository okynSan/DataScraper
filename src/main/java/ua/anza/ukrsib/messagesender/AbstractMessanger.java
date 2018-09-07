/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.messagesender;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class AbstractMessanger {

    protected String URL_STRING;// = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    protected String API_TOKEN;// = "648719423:AAEVkvOzQB44AA70toxFmetLPKGPgyY7W14";
    protected String CHAT_ID;// = "@testofbotchannel";
    protected String MESSAGE;// = "textMessate";

    public AbstractMessanger(String url, String apiToken, String chatId) {
        this.URL_STRING = url;
        this.API_TOKEN = apiToken;
        this.CHAT_ID = chatId;
    }

    public abstract void sendMessage(String message);

}
