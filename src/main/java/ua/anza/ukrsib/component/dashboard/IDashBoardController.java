/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.dashboard;

import java.util.List;
import ua.anza.ukrsib.model.bank.BankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public interface IDashBoardController {

    boolean isMenuButtonVisible();

    public Double getCurrentCapital();

    public List<BankEvent> getTableInfo();

}
