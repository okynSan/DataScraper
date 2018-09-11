/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.WebDriver;

import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Stanislav Tkachenko
 */
public class FormController {
            
    private static FormController me = null;
    
    private static final int BTN_SLEEP_TIME = 1000;
    
    private FormController(){
        
    }
    
    public static FormController getInstance(){
        if (me == null){
            me = new FormController();
        }
        return me;
    }
    
    public void setInputTextById(String id, String text){
        WebElement input = Driver.getInstance().getWebDriver().findElement(By.id(id));
        input.clear();
        input.sendKeys(text);
    }
    
    public void setInputTextByXpath(String xPath, String text){
        WebElement input = Driver.getInstance().getWebDriver().findElement(By.xpath(xPath));
        input.clear();
        input.sendKeys(text);
    }
    
    public void buttonClick(String id){
        WebElement btn = Driver.getInstance().getWebDriver().findElement(By.id(id));
        try {
            Thread.sleep(BTN_SLEEP_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!btn.isEnabled()){
            
        }
        btn.click();
    }
    
    public void fillFields(Map<String, String> allData){
        for(Map.Entry<String, String> cur : allData.entrySet()){
           setInputTextById(cur.getKey(),cur.getValue());
        }
    }
    
    public void fillFieldsByXpath(Map<String, String> allData){
        for(Map.Entry<String, String> cur : allData.entrySet()){
           setInputTextByXpath(cur.getKey(),cur.getValue());
        }
    }
    
    public String getComponentTextXpath(String xPath){
        return  Driver.getInstance().getWebDriver().findElement(By.xpath(xPath)).getText();
    }
    
    public String getComponentValueById(String id){
        return  Driver.getInstance().getWebDriver().findElement(By.id(id)).getAttribute("value");
    }
    
    public void waitUntilFormIsReady(){   
        WebDriverWait wait = Driver.getInstance().getWebDriverWait();
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State: "
                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete");
            }
        });
    }
    
    public void waitUntilComponentVisible(String id){
        WebDriverWait wait = Driver.getInstance().getWebDriverWait();
        wait.until(visibilityOfElementLocated(By.id(id)));
    }
    
}
