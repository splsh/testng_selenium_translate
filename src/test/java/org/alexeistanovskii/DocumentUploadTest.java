package org.alexeistanovskii;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DocumentUploadTest {
    StartPage startPage = new StartPage();
    JavascriptExecutor js = (JavascriptExecutor) startPage.driver;

//    @AfterClass
//    public void endOfTheTest(){
//        startPage.driver.quit();
//    }

    @BeforeClass
    public void prepareToTest(){
        startPage.changePageToDocumentsPage();
        js.executeScript("document.querySelector('input[type=file]').setAttribute('style', 'width:1000px; height:1000px; opacity:1; z-index:1'); "); //omg
        System.out.println("Preparation finished");
    }
    @Test
    public void shouldTranslateFromTheFile(){
        String path = "Z:\\Programming\\Testing homework files\\Useless files\\Test.docx";
        startPage.uploadFile(path);
        startPage.translateFile();
    }

}
