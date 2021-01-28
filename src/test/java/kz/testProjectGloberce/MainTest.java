package kz.testProjectGloberce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTest {
    public static WebDriver session = null;
    public static final String ANSI_RESET = "\u001B[0m"; //Text reset
    public static final String ANSI_YELLOW = "\u001B[33m"; //Yellow

    public void message(String message) {
        System.out.println(ANSI_YELLOW + "Сообщение: " + ANSI_RESET + message);
    }

    public void login(String url, String userName, String userPassword) {
        session.get(url);
        String titleMain = session.getTitle();
        Assert.assertTrue(titleMain.equals("Globerce Admin"));
        session.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        session.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
        session.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        session.get("https://demo-admin.globerce.com/category?gcLocaleCode=en");
    }

    @Before
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
        session = new ChromeDriver();
        session.manage().window().maximize();
    }

    @After
    public void finishBrowser() {
        session.quit();
    }
}
