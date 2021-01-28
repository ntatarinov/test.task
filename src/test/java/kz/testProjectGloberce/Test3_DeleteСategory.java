package kz.testProjectGloberce;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Test3_DeleteСategory extends MainTest {

    @Test
    public void deleteСategory() throws Exception {
        Test1_СreateСategory createCategory = new Test1_СreateСategory();
        createCategory.addСategory();

        WebDriverWait wait = new WebDriverWait(session, Duration.ofSeconds(10));

        session.findElement(By.xpath("//*[@class='listgrid-header-wrapper']")).findElement(By.xpath("//*[@class='list-grid-primary-field']")).click();
        session.findElement(By.xpath("//*[@class='button primary large dropdown-toggle']")).click();
        session.findElement(By.xpath("//*[@class='delete-button']")).click();

        WebElement listGridCategory = wait.until(presenceOfElementLocated(By.id("listgrid-search")));
        listGridCategory.clear();
        listGridCategory.sendKeys(createCategory.codeCategory, Keys.ENTER);
        Thread.sleep(1000);
        Assert.assertEquals("0" , session.findElement(By.className("total-records")).getText());
    }
}
