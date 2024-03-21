package com.inetBanking.testCases;


import com.aventstack.extentreports.utils.FileUtil;
import com.inetBanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.security.Provider;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    ReadConfig readconfig = new ReadConfig();
    public String baseURL =readconfig.getApplicationURL();
    public String username= readconfig.getUserName();
    public String password = readconfig.getPassword();
    public static WebDriver driver;

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String br){

        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("log4j.properties");

        if(br.equals("chrome")){
            driver = new ChromeDriver();
        } else if(br.equals("firefox")){
            driver = new FirefoxDriver();
        } else if(br.equals("ie")){
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException{
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File( System.getProperty("use.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(src,des);
        System.out.println("Screenshot is taken");

    }

}
