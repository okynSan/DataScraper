/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.mbaf.omaselenium.WebDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class Driver {

    private static Driver me = null;

    private static WebDriver webDriver;

    private static final int WAIT_TIME_OUT = 10;

    private Driver() {
        init();
    }

    private Driver(WebDriverEnum driverEnum) {
        init(driverEnum);
    }

    public static Driver getInstance() {
        if (me == null) {
            me = new Driver();
        }
        return me;
    }

    public static Driver getInstance(WebDriverEnum driverEnum) {
        if (me == null) {
            me = new Driver(driverEnum);
        }
        return me;
    }

    private void init() {
        init(WebDriverEnum.CHROM);
    }

    private void init(WebDriverEnum driverEnum) {
        setWebDriver(driverEnum);
    }

    public void setWebDriver(WebDriverEnum driverEnum) {
        webDriver = new WebDriverFactory().getDriver(driverEnum);
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIME_OUT, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    /*
    @author Stanislav_Tkachenko
     */
    public WebDriverWait getWebDriverWait() {
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIME_OUT, TimeUnit.SECONDS);
        return new WebDriverWait(getWebDriver(), WAIT_TIME_OUT);
    }

    public WebDriverWait getWebDriverWait(long timeout) {
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return new WebDriverWait(getWebDriver(), timeout);
    }

    public void goTo(String BaseUrl) {
        getWebDriver().get(BaseUrl);
    }

}
