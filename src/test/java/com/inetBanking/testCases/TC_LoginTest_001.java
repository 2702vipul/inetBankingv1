package com.inetBanking.testCases;

import com.inetBanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass {
    @Test
    public void loginTest() throws IOException {
        driver.get(baseURL);
        logger.info("URL is Opened");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("Entered Username");

        lp.setPassword(password);
        logger.info("Entered Password ");

        lp.clickSubmit();

        if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            Assert.assertTrue(true);
            logger.info("Login Test Passed");
        }
        else{
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Login Test Failed");

        }
    }
}
