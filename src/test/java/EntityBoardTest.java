import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class EntityBoardTest extends BaseTest {

    @Ignore
    @Test
    public void inputTest() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDataEuropean = formatter.format(calendar.getTime());
        final String text = UUID.randomUUID().toString();
        final int number = 12;
        final double decimal = 10.25;
        final String pending = "Pending";
        final String user1Demo = "User 1 Demo";

        WebDriver driver = getDriver();
        ProjectUtils.loginProcedure(driver);

        WebElement tabBoard = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, tabBoard);

        WebElement viewList = driver.findElement(By.xpath("//a[contains(@href, '31')]/i[text()='list']"));
        viewList.click();

        WebElement createNew = driver.findElement(By.xpath("//div[@class = 'card-icon']"));
        createNew.click();

        WebElement stringDropdown = driver.findElement(By.xpath("//div[text()= 'Pending']/.."));
        stringDropdown.click();

        WebElement stringPending = driver.findElement(By.xpath("//div[text()= 'Pending']"));
        stringPending.click();

        WebElement textPlaceholder = driver.findElement(By.id("text"));
        textPlaceholder.sendKeys(text);

        WebElement intPlaceholder = driver.findElement(By.id("int"));
        intPlaceholder.sendKeys(String.valueOf(number));

        WebElement decimalPlaceholder = driver.findElement(By.id("decimal"));
        decimalPlaceholder.sendKeys(String.valueOf(decimal));

        WebElement date = driver.findElement(By.id("date"));
        date.click();

        WebElement dateTime = driver.findElement(By.id("datetime"));
        dateTime.click();

        WebElement saveBtn = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver, saveBtn);

        String recordTitleXpath = String.format("//div[contains(text(), '%s')]", text);
        By stringText = By.xpath(String.format("%s", recordTitleXpath));
        By newRecordStringPending = By.xpath(String.format("%s/../../../td[2]/a/div", recordTitleXpath));
        By newRecordInt = By.xpath(String.format("%s/../../../td[4]/a/div", recordTitleXpath));
        By newRecordDecimal = By.xpath(String.format("%s/../../../td[5]/a/div", recordTitleXpath));
        By newRecordDate = By.xpath(String.format("%s/../../../td[6]/a/div", recordTitleXpath));
        By newRecordDateTime = By.xpath(String.format("%s/../../../td[7]/a/div", recordTitleXpath));
        By newRecordUser1Demo = By.xpath(String.format("%s/../../../td[9]", recordTitleXpath));

        WebElement createdRecordText = driver.findElement(stringText);
        WebElement createdRecordStringPending = driver.findElement(newRecordStringPending);
        WebElement createdRecordInt = driver.findElement(newRecordInt);
        WebElement createdRecordDecimal = driver.findElement(newRecordDecimal);
        WebElement createdRecordDate = driver.findElement(newRecordDate);
        WebElement createdRecordDateTime = driver.findElement(newRecordDateTime);
        WebElement createdRecordUser1Demo = driver.findElement(newRecordUser1Demo);

        Assert.assertEquals(createdRecordText.getText(), text, "Created record text issue");
        Assert.assertEquals(createdRecordStringPending.getText(), pending, "Created record Pending issue");
        Assert.assertEquals(createdRecordInt.getText(), Integer.toString(number), "Created record number issue");
        Assert.assertEquals(createdRecordDecimal.getText(), Double.toString(decimal), "Created record decimal issue");
        Assert.assertEquals(createdRecordDate.getText(), currentDataEuropean, "Created date issue");
        Assert.assertEquals(createdRecordUser1Demo.getText(), user1Demo, "Created user issue");
        deleteRecordByTitle(text);
    }

    private void deleteRecordByTitle(String text) {

        WebDriver driver = getDriver();
        String recordTitleXpath = String.format("//div[contains(text(), '%s')]", text);
        By recordMenuButton = By.xpath(String.format("%s/../../..//button", recordTitleXpath));
        WebElement deleteButton = driver.findElement(By.xpath(String.format("%s/../../..//a[contains(@href, 'delete')]", recordTitleXpath)));
        driver.findElement(recordMenuButton).click();
        ProjectUtils.click(driver, deleteButton);
    }

    private WebDriverWait getWait(int timeoutSecond) {
        return new WebDriverWait(getDriver(), timeoutSecond);
    }

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