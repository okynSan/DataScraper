/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib;

import org.apache.log4j.Logger;
import ua.anza.ukrsib.DAO.bankevent.impl.UkrSibBankEventDaoImpl;
import ua.anza.ukrsib.component.UkrSibPage;
import ua.anza.ukrsib.messagesender.MessangerEnum;
import ua.anza.ukrsib.messagesender.MessangerFactory;
import ua.anza.ukrsib.workflow.AbstractWorkFlow;
import ua.anza.ukrsib.workflow.UkrSibWorkFlow;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class Main {

    public static String LOGIN;
    public static String PASSWORD;
    final static Logger logger = Logger.getLogger("file");

    public static void main(String[] args) throws InterruptedException {
        Main.LOGIN = args[0];
        Main.PASSWORD = args[1];

        while (true) {

            AbstractWorkFlow ukrSib = new UkrSibWorkFlow( new UkrSibPage(),
                    MessangerFactory.getMessenger(MessangerEnum.Telegram)
            );

            try {
                logger.info("Iteration started");
                ukrSib.doWorkFlow();
                logger.info("Iteration finished");
                Thread.sleep(500000);

            } catch (Exception ex) {
                logger.info(ex);
                Thread.sleep(500000);
            }

            ukrSib = null;

        }

    }

}
