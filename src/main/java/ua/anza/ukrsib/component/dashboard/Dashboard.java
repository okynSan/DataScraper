/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.dashboard;

import java.util.List;
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

    @FindBy(how = How.CLASS_NAME, className = "balanceAggregatePanel")
    private List<WebElement> sumFull;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"idae\"]/span[3]/span[1]/span[2]")
    private WebElement sumDecimals;

    @FindBy(how = How.CLASS_NAME, className = "data")
    private List<WebElement> eventTable;

    public IDashBoardController dashBoardController;

    public Dashboard() {
        dashBoardController = new DashBoardControllerImpl(this);
    }

    public WebElement getMenu() {
        return menu;
    }

//    public WebElement getSumFull() {
//        return sumFull;
//    }

    public List<WebElement> getSumFull() {
        return sumFull;
    }
    
    

    public WebElement getSumDecimals() {
        return sumDecimals;
    }

//    public WebElement getEventTable() {
//        return eventTable;
//    }
//
//    public void setEventTable(WebElement eventTable) {
//        this.eventTable = eventTable;
//    }

    public List<WebElement> getEventTable() {
        return eventTable;
    }
    
    
    
    

}
