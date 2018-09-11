/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.WebDriver;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class WebDriverFactory {

    public WebDriver getDriver(WebDriverEnum driverEnum) {

        switch (driverEnum) {
            case CHROM:
                return new WebDriverGC().getWebDriver();
            case FIREFOX:
                return new WebDriverFF().getWebDriver();

        }

        return null;
    }

}
