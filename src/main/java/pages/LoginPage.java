package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    private final By usernameLoc = By.id("user-name");
    private final By passwordLoc = By.id("password");
    private final By loginBtnLoc = By.id("login-button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void validLogin(String username, String password){
        driver.findElement(usernameLoc).sendKeys(username);
        driver.findElement(passwordLoc).sendKeys(password);
        driver.findElement(loginBtnLoc).click();
    }

    public void invalidLogin(String username, String password){
        driver.findElement(usernameLoc).sendKeys(username);
        driver.findElement(passwordLoc).sendKeys(password);
        driver.findElement(loginBtnLoc).click();
    }

    public void invalidLoginWithoutPassword(String username, String password){
        driver.findElement(usernameLoc).sendKeys(username);
        driver.findElement(passwordLoc).sendKeys(password);
        driver.findElement(loginBtnLoc).click();
    }
}
