/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.anza.ukrsib.component.login.Login;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;
import ua.anza.ukrsib.confige.prop.ProjectProperties;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;
import ua.anza.ukrsib.WebDriver.ComponentInitor;
import ua.anza.ukrsib.WebDriver.Driver;
import ua.anza.ukrsib.WebDriver.WebDriverEnum;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class Page {

    protected Login login = null;

    public void initPage() {
        System.out.println(ProjectProperties.getPropertyByKey("page_url"));
        
        try {
            Driver.getInstance(WebDriverEnum.CHROM)
                    .getWebDriver()
                    .get(ProjectProperties.getPropertyByKey("page_url"));
            login = new ComponentInitor().setInitElements(new Login());
        } catch (Exception e) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public abstract List<UkrSibBankEvent> doWorkFlow();

}
