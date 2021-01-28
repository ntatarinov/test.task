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

public class Test2_EditСategory extends MainTest {

    private String codeChangedCategory = new String();
    private String nameEnChangedCategory = new String();
    private String nameRuCategory = new String();
    private Random randNumb = new Random();
    private String titleCategories = new String();

    @Test
    public void editCategory() throws Exception {
        Test1_СreateСategory createCategory = new Test1_СreateСategory();
        createCategory.addСategory();

        codeChangedCategory = "c" + (randNumb.nextInt(999 - 100 + 10) + 100);
        nameEnChangedCategory = "Changed" + createCategory.nameCategory;
        nameRuCategory = "ИзмененТестКатегории" + (randNumb.nextInt(999 - 100 + 10) + 100);
        WebDriverWait wait = new WebDriverWait(session, Duration.ofSeconds(10));
        session.findElement(By.xpath("//*[@class='listgrid-header-wrapper']")).findElement(By.xpath("//*[@class='list-grid-primary-field']")).click();
        titleCategories = session.getTitle();
        Assert.assertTrue(titleCategories.equals("Globerce Admin - " + createCategory.nameCategory + ""));
        session.findElement(By.xpath("//input[@data-mask-type='code']")).clear();
        session.findElement(By.xpath("//input[@data-mask-type='code']")).sendKeys(codeChangedCategory);
        session.findElement(By.xpath("//input[@id=\"fields'name'.value\"]")).clear();
        session.findElement(By.xpath("//input[@id=\"fields'name'.value\"]")).sendKeys(nameEnChangedCategory);
        session.findElement(By.id("field-name")).findElement(By.xpath("//*[@class='show-translations']")).click();
        Thread.sleep(1000);
        session.findElement(By.xpath("//*[@class='button primary toolbar-action gc-btn_sm translation-grid-add ']")).click();
        Thread.sleep(1000);
        session.findElement(By.xpath("//input[@id=\"fields'translatedValue'.value\"]")).sendKeys(nameRuCategory);
        session.findElement(By.xpath("//*[@class='button gc-btn_success translation-submit-button']")).click();
        session.findElement(By.xpath("//button[@class='close']")).click();
        session.findElement(By.xpath("//*[@class='button primary large submit-button primary']")).click();
        session.navigate().back();
        WebElement listGridCategory = wait.until(presenceOfElementLocated(By.id("listgrid-search")));
        listGridCategory.clear();
        listGridCategory.sendKeys(codeChangedCategory, Keys.ENTER);
        Thread.sleep(1000);
        Assert.assertEquals("1" , session.findElement(By.className("total-records")).getText());
    }
}
