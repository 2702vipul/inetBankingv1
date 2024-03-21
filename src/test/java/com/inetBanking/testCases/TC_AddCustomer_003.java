package com.inetBanking.testCases;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AddCustomer_003 extends BaseClass{

    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("User name provided");
        lp.setPassword(password);
        logger.info("Password provided");
        lp.clickSubmit();


//        Thread.sleep(3000);

        AddCustomerPage addCustomer = new AddCustomerPage(driver);
        logger.info("Customer Details provided");

        addCustomer.clickAddNewCustomer();

        addCustomer.custName("Vipul Sharma");
        addCustomer.custGender("male");
        addCustomer.custdob("02","27","2001");
//        Thread.sleep(3000);
        addCustomer.custAddress("India");
        addCustomer.custCity("Noida");
        addCustomer.custState("Up");
        addCustomer.custPinno("243005");
        addCustomer.custTelephoneNo("4578524163");

        String email=randomString()+"@gmail.com";
        addCustomer.custEmailId(email);
        addCustomer.custPassword("abcdef");
        addCustomer.custSubmit();

//        Thread.sleep(3000);
        logger.info("validation started");
        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
        if(res){
            Assert.assertTrue(true);
            logger.info("Test Case passed");
        }
        else {
            captureScreen(driver, "addNewCustomer");
            Assert.fail();
            logger.info("Test Case failed");
        }
    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        return generatedString;
    }
}
