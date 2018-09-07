/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class Dashboard {

    @FindBy(how = How.XPATH, xpath = "/html/body/app-root/div/app-toolbar/mat-toolbar/div[1]/button")
    private WebElement menu;

    @FindBy(how = How.CSS, css = "#idab > span.total.amountPanel > span.sum > span.integer")
    private WebElement sumFull;

    @FindBy(how = How.CSS, css = "#idab > span.total.amountPanel > span.sum > span.fractional")
    private WebElement sumDecimals;

    @FindBy(how = How.CSS, css = "#idd2 > div.data")
    private WebElement eventTable;

    public IDashBoardController dashBoardController;

    public Dashboard() {
        dashBoardController = new DashBoardControllerImpl(this);
    }

    public WebElement getMenu() {
        return menu;
    }

    public WebElement getSumFull() {
        return sumFull;
    }

    public WebElement getSumDecimals() {
        return sumDecimals;
    }

    public WebElement getEventTable() {
        return eventTable;
    }

    public void setEventTable(WebElement eventTable) {
        this.eventTable = eventTable;
    }
    
    

}
