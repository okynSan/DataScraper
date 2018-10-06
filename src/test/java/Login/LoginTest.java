/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.anza.ukrsib.component.dashboard.Dashboard;
import ua.anza.ukrsib.component.login.Login;
import ua.anza.ukrsib.confige.prop.ProjectProperties;
import ua.anza.ukrsib.WebDriver.ComponentInitor;
import ua.anza.ukrsib.WebDriver.Driver;
import ua.anza.ukrsib.WebDriver.WebDriverEnum;

/**
 *
 * @author andrey_zatvornitskiy
 */
@RunWith(JUnitParamsRunner.class)
public class LoginTest {

    private Login login;

    @Before
    public void initLogin() throws IOException {
        System.out.println(ProjectProperties.getPropertyByKey("page_url"));
        Driver.getInstance(WebDriverEnum.CHROM).getWebDriver()
                .get(ProjectProperties.getPropertyByKey("page_url"));

        login = new ComponentInitor().setInitElements(new Login());
    }

    @Test
    @Parameters({
        "634972548, okyn132465, false"
    })
    public void setWebDriverTest(String loginTextField, String password, boolean isLoggedIn) throws Exception {
        login.loginController.setLoginTextField(loginTextField);
        login.loginController.setPassworkdTextField(password);
        login.loginController.clickLoginButton();
        Assert.assertEquals(isLoggedIn, login.loginController.isLoggedIn());
        Dashboard d = login.getDashboard();
        d.dashBoardController.getCurrentCapital();
//        System.out.println();
        d.dashBoardController.getTableInfo().stream().forEach(el -> System.out.println(el.toString()));

    }

    @After
    public void closeDriver() {
        Driver.getInstance().getWebDriver().close();
    }
}
