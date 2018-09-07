/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.mbaf.omaselenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class ComponentInitor {

    public ComponentInitor() {
    }

    public <T> T setInitElements(T component) {
        return PageFactory.initElements(Driver.getInstance(WebDriverEnum.CHROM).getWebDriver(), (Class<T>) component.getClass());
    }

}
