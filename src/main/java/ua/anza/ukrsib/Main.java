/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.anza.ukrsib.DAO.bankevent.impl.BankEventDaoImpl;
import ua.anza.ukrsib.component.UkrSibPage;
import ua.anza.ukrsib.messagesender.MessangerEnum;
import ua.anza.ukrsib.messagesender.MessangerFactory;
import ua.anza.ukrsib.workflow.AbstractWorkFlow;
import ua.anza.ukrsib.workflow.UkrSibWorkFlow;
import ua.mbaf.omaselenium.WebDriver.Driver;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class Main {

    public static String LOGIN;
    public static String PASSWORD;

    public static void main(String[] args) throws InterruptedException {
//        Main.LOGIN = args[1];
//        Main.PASSWORD = args[1];

        AbstractWorkFlow ukrSib = new UkrSibWorkFlow(new BankEventDaoImpl(),
                new UkrSibPage(),
                MessangerFactory.getMessenger(MessangerEnum.Telegram)
        );

        while (true) {
            try {
                ukrSib.doWorkFlow();
                Thread.sleep(10*100*500);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                ukrSib = null;
                Thread.sleep(10*100*500);
            }
        }

    }

}
