package org.alexeistanovskii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/** Here I am trying to make decent Page Object Model.
 *
 * */

public class StartPage {
    WebDriver driver = new ChromeDriver();
    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public StartPage() {
        driver.get("https://translate.google.com/");
    }


    private final By INPUT_FIELD = By.xpath("//textarea[@aria-label='Исходный текст']");
    private final By OUTPUT_FIELD = By.xpath("//span[@class='ryNqvb']");
    private final By CHANGE_LANGUAGE_BUTTON = By.xpath("//span[@class='VfPpkd-YVzG2b']");
    private final By DOCUMENTS_BUTTON = By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Документы']");
    private final By INPUT_DOCUMENT = By.xpath("//input[@type='file']");
    private final By INPUT_DOCUMENT_LABEL = By.xpath("//label[text()='Выбрать на компьютере']");
    private final By DELETE_TEXT = By.xpath("//button[@aria-label='Удалить исходный текст']");
    private final By SPANISH_LANGUAGE = By.xpath("//div[@data-language-code='es'][1]");
    private final By TRANSLATE_FILE_BUTTON= By.xpath("//span[text()='Перевести']");
    private final By DOWNLOAD_FILE_BUTTON= By.xpath("//span[text()='Скачать перевод']");
    private final By SITES_BUTTON = By.xpath("//span[text()= 'Сайты']");
    private final By URL_INPUT_FIELD= By.xpath("//input[@type='text'][@title='URL не должен быть пустым или иметь некорректный формат']");
    private final By TRANSLATE_SITE_BUTTON = By.xpath("//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc zhBWDc Pk9dvb']");

    public void setInputField(String text) {
        driver.findElement(INPUT_FIELD).sendKeys(text);

    }

    public String getOutput() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(OUTPUT_FIELD));
        return driver.findElement(OUTPUT_FIELD).getText();
    }

    public void cleanField() {
        if (driver.findElement(DELETE_TEXT).isDisplayed())
            driver.findElement(DELETE_TEXT).click();
    }

    public void changeLanguage() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(CHANGE_LANGUAGE_BUTTON)).perform();
        driverWait.until(ExpectedConditions.elementToBeClickable(SPANISH_LANGUAGE));
        driver.findElement(SPANISH_LANGUAGE).click();
    }

    public void changePageToDocumentsPage() {
        if (!driver.findElement(INPUT_DOCUMENT_LABEL).isDisplayed()) {
            driverWait.until(ExpectedConditions.elementToBeClickable(DOCUMENTS_BUTTON));
            driver.findElement(DOCUMENTS_BUTTON).click();
        }
    }
    public void uploadFile(String path){
        driverWait.until(ExpectedConditions.elementToBeClickable(INPUT_DOCUMENT));
     driver.findElement(INPUT_DOCUMENT).sendKeys(path);
    }

    public void translateFile (){
        driverWait.until(ExpectedConditions.elementToBeClickable(TRANSLATE_FILE_BUTTON));
        driver.findElement(TRANSLATE_FILE_BUTTON).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(DOWNLOAD_FILE_BUTTON));
        driver.findElement(DOWNLOAD_FILE_BUTTON).click();
    }

    public void changePageToSitesPage() {
        if (!driver.findElement(URL_INPUT_FIELD).isDisplayed()) {
            driverWait.until(ExpectedConditions.elementToBeClickable(SITES_BUTTON));
            driver.findElement(SITES_BUTTON).click();
        }
    }
    public void inputURL(String testURL){
        driverWait.until(ExpectedConditions.elementToBeClickable(URL_INPUT_FIELD));
        driver.findElement(URL_INPUT_FIELD).sendKeys(testURL);
//        Actions actions = new Actions(driver);
//        actions.click(driver.findElement(TRANSLATE_SITE_BUTTON)).perform();
        driver.findElement(TRANSLATE_SITE_BUTTON).click();
    }

}
