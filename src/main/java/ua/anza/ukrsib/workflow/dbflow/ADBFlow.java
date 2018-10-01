/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.workflow.dbflow;

import java.util.List;
import ua.anza.ukrsib.model.AEvents;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class ADBFlow {

    protected List<? extends AEvents> eventList = null;

    public abstract List<? extends AEvents> doWorkFlow();

    public void setFlagForSentEvents(Integer bankEventId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
