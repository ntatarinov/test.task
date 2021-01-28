package kz.testProjectGloberce;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Test1_СreateСategory extends MainTest {

    private Random randNumb = new Random();
    private String titleCategories = new String();
    protected String codeCategory = new String();
    protected String nameCategory = new String();

    @Test
    public void addСategory() throws Exception {
        login("https://demo-admin.globerce.com/login", "admin", "admin");

        titleCategories = session.getTitle();
        codeCategory = "z" + (randNumb.nextInt(999 - 100 + 10) + 100);
        nameCategory = "TestCategory" + (randNumb.nextInt(999 - 100 + 10) + 100);

        Assert.assertTrue(titleCategories.equals("Globerce Admin - Categories"));
        WebDriverWait wait = new WebDriverWait(session, Duration.ofSeconds(10));
        WebElement buttonAddCategory = wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Category')]")));
        buttonAddCategory.click();
        WebElement codeTextCategory = wait.until(presenceOfElementLocated(By.id("field-code")));
        codeTextCategory.findElement(By.xpath("//input[@data-mask-type='code']")).sendKeys(codeCategory);
        session.findElement(By.xpath("//input[@id=\"fields'name'.value\"]")).sendKeys(nameCategory);
        session.findElement(By.id("fields'level'.value")).sendKeys("" + randNumb.nextInt(9));
        session.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        Thread.sleep(1000);
        session.navigate().back();
        WebElement listGridCategory = wait.until(presenceOfElementLocated(By.id("listgrid-search")));
        listGridCategory.sendKeys(codeCategory, Keys.ENTER);
        Thread.sleep(1000);
        Assert.assertEquals("1", session.findElement(By.className("total-records")).getText());
    }
}
