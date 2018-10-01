/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.workflow;

import ua.anza.ukrsib.workflow.dbflow.DAO.bankevent.IBankEventDao;
import ua.anza.ukrsib.component.Page;
import ua.anza.ukrsib.messagesender.AbstractMessanger;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class AbstractWorkFlow {

    protected IBankEventDao bankEventDao;
    protected Page pages;
    protected AbstractMessanger messanger;

    public AbstractWorkFlow(IBankEventDao bankEventDao, Page pages, AbstractMessanger messanger) {
        this.bankEventDao = bankEventDao;
        this.pages = pages;
        this.messanger = messanger;
    }

    public AbstractWorkFlow(Page pages, AbstractMessanger messanger) {
        this.pages = pages;
        this.messanger = messanger;
    }

    public abstract void doWorkFlow();

}
