/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.dashboard;

import java.util.List;
import org.openqa.selenium.WebElement;
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
    public Float getCurrentCapital() {
        List<WebElement> sumElements = this.dbDashboard.getSumFull();

        return this.bankeEventParser.getParsedString(sumElements.get(0).getText());
    }

    @Override
    public List<BankEvent> getTableInfo() {
        List<WebElement> dataElements = this.dbDashboard.getEventTable();

        String bankEvString = dataElements.get(2).getText().replaceAll("\\n", "");
        System.out.println(bankEvString);
        return this.bankeEventParser.getParsedBankEvents(bankEvString);
    }

}
