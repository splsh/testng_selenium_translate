package org.alexeistanovskii;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

/**
 * This class tests main function of Google Translate
 * It translates some words and phrases on 2 languages
 */
public class TextAreaTest {
    StartPage startPage = new StartPage();

    @AfterClass
    public void endOfTheTest(){
        startPage.driver.quit();
    }

    @Test(dataProvider = "englishText")
    public void shouldTranslateItRight(String input) {  //translates english to russian text
        startPage.cleanField();
        String[] inputArray = Arrays.stream(input.split("\\s")).toArray(value -> new String[value]);
        startPage.setInputField(inputArray[0]);
        Assert.assertEquals(startPage.getOutput(), inputArray[1]);
    }

    @Test(dataProvider = "englishPhrases")
    public void shouldTranslatePhrasesRight(String input) {  // translates eng to rus phrases
        startPage.cleanField();
        String[] inputArray = Arrays.stream(input.split("\\+")).toArray(value -> new String[value]);
        startPage.setInputField(inputArray[0]);
        Assert.assertEquals(startPage.getOutput(), inputArray[1]);
    }

    @Test(priority = 3)
    public void beforeSpanishTest(){
        startPage.changeLanguage();
    }   //not elegant way to switch language

    @Test(dataProvider = "spanishText",dependsOnMethods = "beforeSpanishTest")
    public void shouldTranslateSpanishRight(String input){      // translate spanish to russian
        startPage.cleanField();
        String[] inputArray = Arrays.stream(input.split("\\s")).toArray(value -> new String[value]);
        startPage.setInputField(inputArray[0]);
        Assert.assertEquals(startPage.getOutput(), inputArray[1]);
    }


    @DataProvider(name = "englishText")
    public Object[][] englishText() {
        return new Object[][]{
                {"Rigorous Тщательный"},
//                {"Before До"},
//                {"Important Важный"},
//                {"Ambiguous Двусмысленный"},
//                {"Parasite Паразит"},
//                {"Appropriateness Уместность"},
//                {"Therapy Терапия"},
//                {"Vampire Вампир"},
//                {"Quantity Количество"},
                {"Philosopher Философ"}
        };
    }
    @DataProvider(name = "englishPhrases")
    public Object[][] englishPhrases() {
        return new Object[][]{
                {"First one+Первый"},
                {"Violets are blue+Фиалки синие"}
        };
    }

    @DataProvider(name = "spanishText")
        public Object [][] spanishText(){
            return new Object [] [] {
                    {"Sastre Портной"},
                    {"Amor Любовь"},
                    {"Huevo Яйцо"},
                    {"Gracias Спасибо"},
                    {"Que Что"},
            };
        }

}
