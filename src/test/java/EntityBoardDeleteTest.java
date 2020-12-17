import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntityBoardDeleteTest extends BaseTest {

    @Test
    public void newBoardRecordCreation() throws InterruptedException {
        final String text = "kg-" + UUID.randomUUID().toString();
        final int number = 320;
        final double decimal = 0.41;
        final String status = "Pending";
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver,6);
        driver.get("https://ref.eteam.work");

        ProjectUtils.loginProcedure(driver);
        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, tab);

        WebElement buttonBoard = driver.findElement(By.xpath("//i[text()='create_new_folder']"));
        ProjectUtils.click(driver, buttonBoard);
        WebElement menuString = driver.findElement(By.xpath("//button[@data-id='string']"));
        ProjectUtils.click(driver,menuString);

        WebElement optionPending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//a[@role='option']/span[contains(text(), '" + status + "')]/..")));
        ProjectUtils.click(driver, optionPending);

        WebElement fieldText= driver.findElement(By.id("text"));
        fieldText.sendKeys(text);
        WebElement fieldInt = driver.findElement(By.id("int"));
        fieldInt.sendKeys(String.valueOf(number));
        WebElement fieldDecimal = driver.findElement(By.id("decimal"));
        fieldDecimal.sendKeys(String.valueOf(decimal));

        WebElement buttonSave = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver, buttonSave);

        WebElement viewList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//a[contains(@href, '31')]/i[text()='list']")));
        ProjectUtils.click(driver, viewList);

        WebElement newRecord = driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[1]/td[3]/a/div"));
        Assert.assertEquals(newRecord.getText(), text, "No matching created record found.");

        WebElement menuActions = driver.findElement(By.xpath("//i[text() = 'menu']/.."));
        ProjectUtils.click(driver, menuActions);
        WebElement optionDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//ul[@role='menu']/li[3]/a[text()= 'delete']")));
        ProjectUtils.click(driver, optionDelete);
        WebElement recycleBin = driver.findElement(By.xpath("//li/a/i[text()='delete_outline']"));
        ProjectUtils.click(driver, recycleBin);

        WebElement deletedRecord = driver.findElement(By.xpath("//span[contains(text(), 'Text:')]/b"));
        Assert.assertEquals(deletedRecord.getText(), text, "No matching deleted record found.");

        WebElement linkDeletePermanent = driver.findElement(By.xpath("//a[contains(text(), 'delete permanently')]"));
        ProjectUtils.click(driver, linkDeletePermanent);

        WebElement emptyRecycleBin = driver.findElement(By.xpath(
            "//div[contains(text(), 'Good job with housekeeping! Recycle bin is currently empty!')]"));
        Assert.assertNotNull(emptyRecycleBin, "No empty recycle bin message found.");
    }
}
