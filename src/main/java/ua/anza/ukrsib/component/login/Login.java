/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ua.anza.ukrsib.component.dashboard.Dashboard;
import ua.anza.ukrsib.WebDriver.ComponentInitor;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class Login {

    private ComponentInitor initor = new ComponentInitor();

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"id10\"]")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"idc\"]")
    private WebElement loginTextField;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"ide\"]")
    private WebElement passworkdTextField;

    @FindBy(how = How.CLASS_NAME, className = "actionLogout")
    private WebElement logOut;

    public ILoginController loginController;

    Dashboard dashboard;

    public Login() {
        loginController = new LoginControllerImpl(this);
        dashboard = initor.setInitElements(new Dashboard()); // PageFactory.initElements(Driver.getInstance(WebDriverEnum.CHROM).getWebDriver(), Dashboard.class);

    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getLoginTextField() {
        return loginTextField;
    }

    public WebElement getPassworkdTextField() {
        return passworkdTextField;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public WebElement getLogOut() {
        return logOut;
    }

    
    
}
