/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.component.login;

/**
 *
 * @author andrey_zatvornitskiy
 */
public interface ILoginController {

    public void clickLoginButton();

    public void setLoginTextField(String loginTextField);

    public void setPassworkdTextField(String passworkdTextField);

    public boolean isLoggedIn();
}
