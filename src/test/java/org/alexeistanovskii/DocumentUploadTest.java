package org.alexeistanovskii;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/** Idea was to test translate document function, but it works not always even manually, also i tried to read text
 * from downloaded file, but input streams don't work with docx files properly, and Google Translate don't work with .txt
 * */
public class DocumentUploadTest {
    StartPage startPage = new StartPage();
    JavascriptExecutor js = (JavascriptExecutor) startPage.driver;

    @AfterClass     //closes window
    public void endOfTheTest(){
        startPage.driver.quit();
    }

    @BeforeClass
    public void prepareToTest(){  //switches to document translate, and makes upload button visible
        startPage.changePageToDocumentsPage();
        js.executeScript("document.querySelector('input[type=file]').setAttribute('style', 'width:1000px; height:1000px; opacity:1; z-index:1'); "); //omg
        System.out.println("Preparation finished");
    }
    @Test
    public void shouldTranslateFromTheFile(){       // upload/translate/download
        String path = "Z:\\Programming\\Testing homework files\\Useless files\\Test.docx";
        startPage.uploadFile(path);
        startPage.translateFile();
    }

}
