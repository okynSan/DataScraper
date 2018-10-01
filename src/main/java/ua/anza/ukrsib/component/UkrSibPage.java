/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component;

import java.util.List;
import org.apache.log4j.Logger;
import ua.anza.ukrsib.Main;
import ua.anza.ukrsib.WebDriver.Driver;
import ua.anza.ukrsib.component.dashboard.Dashboard;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class UkrSibPage extends Page {

    final static Logger logger = Logger.getLogger("file");

    public UkrSibPage() {
        this.initPage();
    }

    @Override
    public List<UkrSibBankEvent> doWorkFlow() {

        try {
            login.loginController.setLoginTextField(Main.LOGIN);
            login.loginController.setPassworkdTextField(Main.PASSWORD);
            login.loginController.clickLoginButton();
            if (login.loginController.isLoggedIn()) {
                throw new Exception("not logged in");
            }

            Dashboard d = login.getDashboard();
            List<UkrSibBankEvent> bankEvents = d.dashBoardController.getTableInfo();
            bankEvents.get(0).setActualSum(d.dashBoardController.getCurrentCapital());
            login.loginController.logOut();
            Driver.closeConnetction();
            return bankEvents;
        } catch (Exception ex) {
            logger.warn(ex);
            Driver.closeConnetction();
        }
        return null;
    }

}
