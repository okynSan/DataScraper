/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.messagesender;

import ua.anza.ukrsib.confige.prop.ProjectProperties;
import ua.anza.ukrsib.messagesender.telegram.TelegramMessanger;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class MessangerFactory {

    public static AbstractMessanger getMessenger(MessangerEnum messangerEnum) {
        switch (messangerEnum) {
            case Telegram:
                return new TelegramMessanger(ProjectProperties.getPropertyByKey("url"),
                        ProjectProperties.getPropertyByKey("apiToken"),
                        ProjectProperties.getPropertyByKey("chatId")
                );
            default:
                new TelegramMessanger(null, null, null);
        }
        return null;

    }

}
