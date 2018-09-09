/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.login;

import java.util.logging.Level;
import java.util.logging.Logger;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;

public class LoginControllerImpl implements ILoginController {

    Login lg;

    public LoginControllerImpl(Login login) {
        this.lg = login;
    }

    @Override
    public void clickLoginButton() {
        this.lg.getLoginButton().click();
    }

    @Override
    public void setLoginTextField(String loginTextField) throws Exception {
        try {
            this.lg.getLoginTextField().sendKeys(loginTextField);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void setPassworkdTextField(String passworkdTextField) {
        try {
            this.lg.getPassworkdTextField().sendKeys(passworkdTextField);
        } catch (Exception ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isLoggedIn() {
        try {
            return this.lg.dashboard.dashBoardController.isMenuButtonVisible();
        } catch (Exception ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void logOut() {
        this.lg.getLogOut().click();
    }

}
