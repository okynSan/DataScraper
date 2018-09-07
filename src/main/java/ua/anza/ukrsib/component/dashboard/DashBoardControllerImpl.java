/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.dashboard;

import java.util.List;
import ua.anza.ukrsib.model.bank.BankEvent;
import ua.anza.ukrsib.utils.parse.IBankeEventParser;
import ua.anza.ukrsib.utils.parse.impl.BankEventParserImpl;

public class DashBoardControllerImpl implements IDashBoardController {

    Dashboard dbDashboard;
    IBankeEventParser bankeEventParser;

    public DashBoardControllerImpl(Dashboard dbDashboard) {
        this.dbDashboard = dbDashboard;
        this.bankeEventParser = new BankEventParserImpl();
    }

    @Override
    public boolean isMenuButtonVisible() {
        boolean isVisible = false;
        try {
            if (this.dbDashboard.getMenu().isDisplayed()) {
                isVisible = true;
            }
        } catch (Exception ex) {
            isVisible = false;
        }
        return isVisible;
    }

    @Override
    public Double getCurrentCapital() {
        String sumFull = this.dbDashboard.getSumFull().getText();
        String sumDecimals = this.dbDashboard.getSumDecimals().getText();

        return Double.parseDouble(sumFull.concat(sumDecimals).replaceAll(",", "."));
    }

    @Override
    public List<BankEvent> getTableInfo() {
        String a = this.dbDashboard.getEventTable().getText().replaceAll("\\n", "");
        return this.bankeEventParser.getParsedBankEvents(a);
    }

}
