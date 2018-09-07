/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.workflow;

import java.util.List;
import ua.anza.ukrsib.DAO.bankevent.IBankEventDao;
import ua.anza.ukrsib.component.Page;
import ua.anza.ukrsib.messagesender.AbstractMessanger;
import ua.anza.ukrsib.model.bank.BankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class UkrSibWorkFlow extends AbstractWorkFlow {

    public UkrSibWorkFlow(IBankEventDao bankEventDao, Page pages, AbstractMessanger messanger) {
        super(bankEventDao, pages, messanger);
    }

    @Override
    public void doWorkFlow() {
        List<BankEvent> bankEvents = pages.doWorkFlow();
        bankEvents.stream().forEach(bE -> {
            if (this.bankEventDao.isBankEventIfNotExists(bE)) {
                this.bankEventDao.saveBankEvent(bE);
            }
        });
        
        //TODO: BankeEvent
        List<Float> spentSums = this.bankEventDao.getUnCheckedSums();
        spentSums.stream().forEach(sums -> this.messanger.sendMessage(new StringBuilder("You spent ")
                .append(sums)
                .append("").toString()));
    }

}
