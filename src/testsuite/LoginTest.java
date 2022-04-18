package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login ";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        // Enter username
        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("tomsmith");
        // Enter password
        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword!");
        // click on login button
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
        // verify text "Secure Area"
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]")).getText();
        System.out.println(actualText);
        Assert.assertEquals("Secure Area is not visible" ,expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Enter invalid username
        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("tomsmith1");
        // Enter valid password
        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword!");
        // click on login button
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
        // verify text "Secure Area"
        String expectedText = "Your username is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error'] ")).getText();
        String originalText = actualText.substring(0,25);
        System.out.println(actualText);
        Assert.assertEquals("Error message not visible",expectedText, originalText);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // Enter invalid username
        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("tomsmith");
        // Enter valid password
        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword");
        // click on login button
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();
        // verify text "Secure Area"
        String expectedText = "Your password is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error'] ")).getText();
        String originalText =actualText.substring(0,25);
        System.out.println(actualText);
        Assert.assertEquals("Error message not visible",expectedText, originalText);

    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
