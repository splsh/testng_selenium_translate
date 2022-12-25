package org.alexeistanovskii;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
/** This class tests translate page function, with data providers, works perfectly
* */
public class SitesTranslationTest {
    StartPage startPage = new StartPage();
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) startPage.driver;
    String defaultPage = startPage.driver.getWindowHandle();
    Set<String> currentWindows;
    String result;
        @AfterClass     //closes window
    public void endOfTheTest(){
        startPage.driver.quit();
    }

    @BeforeClass
    public void changePageToTranslateSitePage() {
        startPage.changePageToSitesPage();
        System.out.println("Preparation finished");
    }

    @Test(dataProvider = "sitesAndXpath")
    public void inputTestURL1(String input) {
        startPage.cleanURLField();
        String[] inputArray = Arrays.stream(input.split("@@@")).toArray(value -> new String[value]);
        startPage.inputURL(inputArray[0]);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        currentWindows = startPage.driver.getWindowHandles();
        for (String w : currentWindows) {
            if (!defaultPage.equals(w)) {
                result = w;
            }
        }
        startPage.driver.switchTo().window(result);
        startPage.driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(inputArray[1])));
        Assert.assertTrue(startPage.driver
                .findElement(By
                        .xpath(inputArray[1])).isDisplayed());
        startPage.driver.switchTo().window(defaultPage);
    }


    @DataProvider(name = "sitesAndXpath")
    public Object[][] sitesAndXpath() {
        return new Object[][]{
                {"https://dwarffortresswiki.org/index.php/DF2014:Gem_cutter @@@ //a[@title='DF2014: Драгоценный камень']"},
                {"https://dwarffortresswiki.org/index.php/DF2014:Climber @@@ //a[@title='DF2014:Стена']"},
                {"https://dwarffortresswiki.org/index.php/DF2014:Comedian @@@ //a[@title='DF2014: Социальные навыки']"}
        };
    }
}
