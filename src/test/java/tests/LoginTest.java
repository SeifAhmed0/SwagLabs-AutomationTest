package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTest {
    private final By errorMsgLoc = new By.ByCssSelector("h3[data-test=\"error\"]");

    @Test
    public void validLoginTest(){
        LoginPage lgnPage = new LoginPage(driver);
        JsonNode data = DataDriven.jsonReader();
        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username,password);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void invalidLoginTest(){
        LoginPage lgnPage = new LoginPage(driver);
        JsonNode data = DataDriven.jsonReader();
        String username = data.get("invalidLogin").get("username").asText();
        String password = data.get("invalidLogin").get("password").asText();
        lgnPage.invalidLogin(username,password);
        Assert.assertTrue(driver.findElement(errorMsgLoc).getText().contains("Username and password do not match"));
    }

    @Test
    public void invalidLoginWithoutPasswordTest(){
        LoginPage lgnPage = new LoginPage(driver);
        JsonNode data = DataDriven.jsonReader();
        String username = data.get("invalidLoginWithoutPassword").get("username").asText();
        String password = data.get("invalidLoginWithoutPassword").get("password").asText();
        lgnPage.invalidLoginWithoutPassword(username,password);
        Assert.assertTrue(driver.findElement(errorMsgLoc).getText().contains("Password is required"));
    }
}
