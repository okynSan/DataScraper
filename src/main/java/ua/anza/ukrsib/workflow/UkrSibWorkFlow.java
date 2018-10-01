/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.workflow;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.log4j.Logger;
import ua.anza.ukrsib.dbflow.DAO.bankevent.IBankEventDao;
import ua.anza.ukrsib.WebDriver.Driver;
import ua.anza.ukrsib.component.Page;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;
import ua.anza.ukrsib.dbflow.ADBFlow;
import ua.anza.ukrsib.dbflow.DBUkrSibFlow;
import ua.anza.ukrsib.messagesender.AbstractMessanger;
import ua.anza.ukrsib.model.AEvents;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class UkrSibWorkFlow extends AbstractWorkFlow {

    final static Logger ukrSibWorkFlowLogger = Logger.getLogger("messenger");
    private ADBFlow aDBFlow = null;
    List<UkrSibBankEvent> bankEvents = null;

    public UkrSibWorkFlow(IBankEventDao bankEventDao, Page pages, AbstractMessanger messanger) {
        super(bankEventDao, pages, messanger);
    }

    public UkrSibWorkFlow(Page pages, AbstractMessanger messanger) {
        super(pages, messanger);
    }

//    @Override
//    public void doWorkFlow() {
//        List<BankEvent> bankEvents = null;
//
//        bankEvents = pages.doWorkFlow();
//
//        if (bankEvents != null) {
//            for (UkrSibBankEvent be : bankEvents) {
//                System.out.println(be.toString());
//
//                if (!this.bankEventDao.isBankEventIfNotExists(be)) {
//                    this.bankEventDao.saveBankEvent(be);
//                }
//            }
//
//            List<BankEvent> bankEvList = this.bankEventDao.getUnCheckedSums();
//            if (bankEvList.isEmpty()) {
//                ukrSibWorkFlowLogger.info("No new events");
//            }
//
//            bankEvList.stream().forEach(bk -> {
//                this.messanger.sendMessage(new StringBuilder("")
//                        .append(bk.getSumSpent() < 0 ? "You spent " + bk.getSumSpent() : "Yout received " + bk.getSumSpent())
//                        .append(bk.getActualSum() != 0 ? "; actual sum: " + bk.getActualSum() : "")
//                        .append("").toString());
//                this.bankEventDao.setChecked(bk.getBankEventId());
//            }
//            );
//
//        } else {
//            ukrSibWorkFlowLogger.info("No new events");
//        }
//        MySqlConnection.closConnection();
//    }
    @Override
    public void doWorkFlow() {

        aDBFlow = new DBUkrSibFlow(pages.doWorkFlow());
        List<UkrSibBankEvent> ukrSibEventList = (List<UkrSibBankEvent>) aDBFlow.doWorkFlow();

        if (ukrSibEventList.isEmpty()) {
            ukrSibWorkFlowLogger.info("No new events");
        } else {
            ukrSibEventList.stream().forEach(bk -> {
                this.messanger.sendMessage(new StringBuilder("")
                        .append(bk.getSumSpent() < 0 ? "You spent " + bk.getSumSpent() : "Yout received " + bk.getSumSpent())
                        .append(bk.getActualSum() != 0 ? "; actual sum: " + bk.getActualSum() : "")
                        .append("").toString());
                aDBFlow.setFlagForSentEvents(bk.getBankEventId());
            }
            );
        }
        MySqlConnection.closConnection();
    }

}
