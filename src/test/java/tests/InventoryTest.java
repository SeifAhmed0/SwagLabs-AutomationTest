package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTest {
    @DataProvider(name = "icons-provider")
    public Object[][] iconsProvider(){
        return new Object[][] {
                {"LinkedIn"},
                {"Facebook"},
                {"X"}
        };
    }

    @Test
    public void inventoryTest(){
        InventoryPage invPage = new InventoryPage(driver);
        LoginPage lgnPage = new LoginPage(driver);
        JsonNode data = DataDriven.jsonReader();
        SoftAssert soft = new SoftAssert();

        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username, password);

        soft.assertTrue(invPage.pageTitle());
        soft.assertTrue(invPage.cartIconDisplayed());
        soft.assertEquals(invPage.productsNum(),6);
        soft.assertAll();
    }

    @Test(dataProvider = "icons-provider", dependsOnMethods = "inventoryTest")
    public void socialIconsTest(String icon){
        LoginPage lgnPage = new LoginPage(driver);
        InventoryPage invPage = new InventoryPage(driver);
        JsonNode data = DataDriven.jsonReader();
        SoftAssert soft = new SoftAssert();

        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username, password);

        boolean actualIconURL = invPage.iconClick(icon);

        soft.assertTrue(actualIconURL);
        soft.assertAll();
    }
}
