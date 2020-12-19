import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;;

public class EntityBoardEditTest extends BaseTest {

    @Test

    public void editBoard() throws InterruptedException {

        WebDriver driver = getDriver();
        WebElement board = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, board);

        WebElement newFolder = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        newFolder.click();

        WebElement text = driver.findElement(By.xpath("//textarea[@id='text']"));
        text.sendKeys("my test");

        WebElement integer = driver.findElement(By.xpath("//input[@id='int']"));
        integer.sendKeys(String.valueOf(20));

        WebElement decimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        decimal.sendKeys(String.valueOf(22.5));

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveButton);

        WebElement dashboard = driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='dashboard']"));
        dashboard.click();

        WebElement list = driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='list']"));
        list.click();

        WebElement container = driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]"));
        container.click();

        WebElement edit = driver.findElement(By.xpath("//a[text()='edit']"));
        ProjectUtils.click(driver, edit);

        WebElement pending = driver.findElement(By.xpath("//div[contains(text(),'Pending')]"));
        pending.click();
        Thread.sleep(2000);

        WebElement onTrack = driver.findElement(By.xpath("//span[contains(text(),'On track')]"));
        onTrack.click();

        WebElement text1 = driver.findElement(By.xpath("//textarea[@id='text']"));
        text1.clear();

        WebElement newText = driver.findElement(By.xpath("//textarea[@id='text']"));
        newText.sendKeys("my test changed");

        WebElement saveButton1 = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveButton1);

        String result = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]/div[1]")).getText();
        Assert.assertEquals(result, "my test changed");

        WebElement container1 = driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]"));
        container1.click();
        Thread.sleep(2000);

        WebElement delete = driver.findElement(By.xpath("//a[text()='delete']"));
        delete.click();

        driver.findElement(By.xpath("//ul[@class='pa-nav-pills-small nav nav-pills nav-pills-primary']//i[text()='list']")).click();
        boolean emptyField = driver.findElements(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]")).size() < 1;
        Assert.assertTrue(emptyField);
    }
}


