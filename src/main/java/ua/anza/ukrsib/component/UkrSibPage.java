/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component;

import java.util.List;
import ua.anza.ukrsib.Main;
import ua.anza.ukrsib.component.dashboard.Dashboard;
import ua.anza.ukrsib.model.bank.BankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class UkrSibPage extends Page {

    public UkrSibPage() {
        this.initPage();
    }

    @Override
    public List<BankEvent> doWorkFlow() {

        try {
            login.loginController.setLoginTextField(Main.LOGIN);
            login.loginController.setPassworkdTextField(Main.PASSWORD);
            login.loginController.clickLoginButton();
            Dashboard d = login.getDashboard();
            List<BankEvent> bankEvents = d.dashBoardController.getTableInfo();
            bankEvents.get(0).setActualSum(d.dashBoardController.getCurrentCapital());

            return bankEvents;
        } catch (Exception ex) {
            
        } finally {
            login.loginController.logOut();
        }
        return null;
    }

}
