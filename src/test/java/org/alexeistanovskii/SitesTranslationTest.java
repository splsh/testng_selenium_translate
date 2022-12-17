package org.alexeistanovskii;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SitesTranslationTest {
    StartPage startPage = new StartPage();
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) startPage.driver;

    //    @AfterClass     //closes window
//    public void endOfTheTest(){
//        startPage.driver.quit();
//    }

    @BeforeClass
    public void changePageToTranslateSitePage(){
        startPage.changePageToSitesPage();
        System.out.println("Preparation finished");
    }

    @Test
    public void inputTestURL(){
        String testUrl = "https://dwarffortresswiki.org/index.php/DF2014:Gem_cutter";
        startPage.inputURL(testUrl);
    }

}
