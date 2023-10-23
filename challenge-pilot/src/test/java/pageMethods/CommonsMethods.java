package pageMethods;

import configuration.Iproperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.awaitility.Awaitility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonsMethods {
    public static WebDriver driver;
    public Iproperties props = ConfigFactory.create(Iproperties.class);
    public String os;
    public int timeWaitStandard = 8;

    @Before(value = "@run", order = 2)
    public void goToBaseUrlChrome(Scenario scenario) {
        os = System.getProperty("os");
        String pathChromedriver;
        switch (os) {
            case "linux":
                pathChromedriver = "src/test/resources/drivers/linux/chromedriver";
                break;
            default:
                pathChromedriver = "src/test/resources/drivers/windows/chromedriver.exe";
                break;
        }
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
    }

    public void goToBaseUrl() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(props.baseUrl());
    }

    public void takeScreenshots(String screenshotName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));
    }

    @After
    public void afterTest(Scenario scenario) {
        System.out.println("FINALIZANDO TEST");
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
            }
            driver.quit();
        }
    }

    public void elementClick(By element) {
        Awaitility.await().atMost(timeWaitStandard, TimeUnit.SECONDS).until(() -> {
            try {
                scrollToElement(element);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                Thread.sleep(500);
                driver.findElement(element).click();
                return true;
            } catch (Throwable throwable) {
                System.out.println("ERROR => " + throwable);
                return false;
            }
        });
    }

    public void elementClick(WebElement element) {
        Awaitility.await().atMost(timeWaitStandard, TimeUnit.SECONDS).until(() -> {
            try {
                scrollToElement(element);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                Thread.sleep(500);
                element.click();
                return true;
            } catch (Throwable throwable) {
                System.out.println("ERROR => " + throwable);
                return false;
            }
        });
    }

    public void elementSendText(By element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeWaitStandard));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).sendKeys(text);
    }

    public void scrollToElement(By element) {
        WebElement elemento = new WebDriverWait(driver, Duration.ofSeconds(timeWaitStandard)).until(ExpectedConditions.visibilityOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elemento);
    }

    public void scrollToElement(WebElement element) {
        WebElement elemento = new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elemento);
    }

    public void elementWaitDisplayed(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeWaitStandard));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void elementWaitForPresence(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeWaitStandard));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public String getElementText(By element) {
        return driver.findElement(element).getText();
    }

    public List<WebElement> castToList(By element) {
        return driver.findElements(element);
    }
}