/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.DAO.bankevent;

import java.util.List;
import ua.anza.ukrsib.model.bank.BankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public interface IBankEventDao {

    public void saveBankEvent(BankEvent bankEvent);
    public List<BankEvent> getUnCheckedSums();
    public boolean isBankEventIfNotExists(BankEvent bankEvent);
    public void setChecked(Integer bankEventId);
}
