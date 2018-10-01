/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.dbflow;

import java.util.List;
import ua.anza.ukrsib.dbflow.DAO.bankevent.IBankEventDao;
import ua.anza.ukrsib.dbflow.DAO.bankevent.impl.UkrSibBankEventDaoImpl;
import ua.anza.ukrsib.model.AEvents;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class DBUkrSibFlow extends ADBFlow {

    private IBankEventDao ukrSibBankEventDao;

    public DBUkrSibFlow(List<UkrSibBankEvent> eventList) {
        this.eventList = eventList;
        this.ukrSibBankEventDao = new UkrSibBankEventDaoImpl();
    }

    @Override
    public List<? extends AEvents> doWorkFlow() {
        if (this.eventList != null) {
            this.eventList.stream().filter((be)
                    -> (!this.ukrSibBankEventDao.isBankEventIfNotExists((UkrSibBankEvent) be)))
                    .forEachOrdered((be) -> {
                        this.ukrSibBankEventDao.saveBankEvent((UkrSibBankEvent) be);
                    });
        }
        return this.ukrSibBankEventDao.getUnCheckedSums();
    }

    public void setFlagForSentEvents(Integer bankEventId) {
        this.ukrSibBankEventDao.setChecked(bankEventId);
    }
}
