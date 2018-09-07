/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.login;

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
    public void setLoginTextField(String loginTextField) {
        this.lg.getLoginTextField().sendKeys(loginTextField);
    }

    @Override
    public void setPassworkdTextField(String passworkdTextField) {
        this.lg.getPassworkdTextField().sendKeys(passworkdTextField);
    }

    @Override
    public boolean isLoggedIn() {
        return this.lg.dashboard.dashBoardController.isMenuButtonVisible();
    }

}
