package org.alexeistanovskii;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class SitesTranslationTest {
    StartPage startPage = new StartPage();
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) startPage.driver;
    String defaultPage = startPage.driver.getWindowHandle();
    Set<String> currentWindows;
    String result;
    //    @AfterClass     //closes window
//    public void endOfTheTest(){
//        startPage.driver.quit();
//    }

    @BeforeClass
    public void changePageToTranslateSitePage(){
        startPage.changePageToSitesPage();
        System.out.println("default page is: " + defaultPage);
        System.out.println("Preparation finished");
    }

    @Test
    public void inputTestURL(){
        String testUrl = "https://dwarffortresswiki.org/index.php/DF2014:Gem_cutter";
        System.out.println(defaultPage);
        startPage.inputURL(testUrl);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        currentWindows=startPage.driver.getWindowHandles();
       for (String w: currentWindows ){
           if(!defaultPage.equals(w)){
               result=w;
               System.out.println("result: " + result);
           }
       }
        startPage.driver.switchTo().window(result);
        startPage.driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='DF2014: Драгоценный камень']")));
        System.out.println(startPage.driver.getWindowHandle());
        Assert.assertTrue(startPage.driver
                .findElement(By
                        .xpath("//a[@title='DF2014: Драгоценный камень']")).isDisplayed());
    }

}
//CDwindow-8FFC5753271D3AA0519270A2502705D1