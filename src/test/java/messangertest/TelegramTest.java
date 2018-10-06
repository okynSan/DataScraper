/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messangertest;

import org.junit.Test;
import ua.anza.ukrsib.messagesender.AbstractMessanger;
import ua.anza.ukrsib.messagesender.MessangerEnum;
import ua.anza.ukrsib.messagesender.MessangerFactory;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class TelegramTest {

    @Test
    public void telegramTest() {
        AbstractMessanger telegram = MessangerFactory.getMessenger(MessangerEnum.Telegram);
        telegram.sendMessage(new StringBuilder("aksjd").append(" ").append("test").toString());
    }

}
