import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;

import java.awt.*;

import static runner.ProjectUtils.click;

public class EntityBoardEditTest extends BaseTest {

    @Test
    public void editBoard() throws InterruptedException {
        WebDriver driver = getDriver();
        WebElement board = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, board);

        driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]")).click();
        driver.findElement(By.xpath("//textarea[@id='text']")).sendKeys("my test");
        driver.findElement(By.xpath("//input[@id='int']")).sendKeys(String.valueOf(20));
        driver.findElement(By.xpath("//input[@id='decimal']")).sendKeys(String.valueOf(22.5));

        ProjectUtils.click(driver, driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']")));
        Thread.sleep(3000);

        driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='dashboard']")).click();
        driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='list']")).click();
        driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]")).click();
        ProjectUtils.click(driver, driver.findElement(By.xpath("//a[text()='edit']")));
        driver.findElement(By.xpath("//div[contains(text(),'Pending')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'On track')]")).click();
        Thread.sleep(2000);
        WebElement text = driver.findElement(By.xpath("//textarea[@id='text']"));
        text.clear();
        driver.findElement(By.xpath("//textarea[@id='text']")).sendKeys("my test changed");
        Thread.sleep(2000);
        ProjectUtils.click(driver, driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']")));

        String result = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]/div[1]")).getText();
        Assert.assertEquals(result, "my test changed");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='delete']")).click();

        driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='list']")).click();
        boolean emptyField = driver.findElements(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]")).size() < 1;
        Assert.assertTrue(emptyField);
    }
}


