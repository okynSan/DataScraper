/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component;

import ua.anza.ukrsib.WebDriver.ComponentInitor;

/**
 *
 * @author andrey_zatvornitskiy
 */
public abstract class BasePage {

    private ComponentInitor initor = new ComponentInitor();

    public abstract void doWorkFlow();

    public ComponentInitor getInitor() {
        return initor;
    }

}
