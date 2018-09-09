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
import ua.anza.ukrsib.model.bank.BankEvent;
import ua.mbaf.omaselenium.WebDriver.ComponentInitor;
import ua.mbaf.omaselenium.WebDriver.Driver;
import ua.mbaf.omaselenium.WebDriver.WebDriverEnum;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class Page {

    protected Login login = null;

    public void initPage() {
        try {
            Driver.getInstance(WebDriverEnum.CHROM)
                    .getWebDriver()
                    .get(ProjectProperties.getPropertyByKey("page_url"));
            login = new ComponentInitor().setInitElements(new Login());
        } catch (Exception e) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public abstract List<BankEvent> doWorkFlow();

}
