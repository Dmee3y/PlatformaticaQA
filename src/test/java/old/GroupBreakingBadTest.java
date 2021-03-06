package old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertEquals;

@Ignore
public class GroupBreakingBadTest extends BaseTest {

    @Test
    public void nataliaVats() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://www.aerotek.com/en/");

        WebElement button = driver.findElement(By.xpath("(//button[contains(text(), 'SEARCH')])[2]"));
        button.click();
        Thread.sleep(3000);

        WebElement input = driver.findElement(By.xpath("//input[contains(@class, 'ph-a11y-location-box')]"));
        assertEquals(input.getAttribute("placeholder"), "Enter City, State or Zip");
    }

    @Test
    public void alexeySemenov() throws InterruptedException {

        WebDriver browser = getDriver();
        browser.get("https://www.jizo.com");

        WebElement inputSearch = browser.findElement(By.xpath("//input[@id='searchCity']"));
        inputSearch.sendKeys("Rus");
        Thread.sleep(3000);

        WebElement menuElement = browser.findElement(By.xpath("//*[@id='CityList']/div[1]/div[2]/h4"));
        assertEquals(menuElement.getText().toLowerCase(), "russia");
    }

    @Test
    public void svitlanaVarakuta() {

        WebDriver driver = getDriver();
        driver.get("https://en.wikipedia.org/wiki/List_of_national_parks_of_the_United_States");

        WebElement link = driver.findElement(By.xpath("//div[5]/div[1]/div[2]/nav[1]/div/ul/li[3]/a"));
        link.click();

        assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/w/index.php?title=List_of_national_parks_of_the_United_States&action=history");
    }

    @Test
    public void searchFieldTestPK() throws InterruptedException {

        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);
        driver.get("https://www.wikipedia.org");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//strong[contains(text(),'English')]")).click();
        WebElement search = driver.findElement(By.xpath("//input[@id='searchInput']"));

        actions.moveToElement(search).sendKeys("Selenium WebDriver")
                .sendKeys(Keys.ENTER)
                .build().perform();
        driver.navigate().refresh();
        Thread.sleep(2000);

        assertEquals(getDriver().getTitle(), "Wikipedia, the free encyclopedia");
    }

    @Test
    public void testTatyanaAlexandrova() {

        WebDriver browser = getDriver();
        browser.get("http://selenium.dev");

        WebElement name = browser.findElement(By.id("gsc-i-id1"));
        name.click();
        name.sendKeys("developers", Keys.ENTER);

        Assert.assertTrue(browser.getPageSource().contains("About"));
    }

    @Test
    public void tatyanaPusFirstTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://3ddd.ru/");

        WebElement button = driver.findElement(By.xpath("//*[text()='3D Модели']"));
        button.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://3ddd.ru/3dmodels");
    }

    @Test
    public void tatyanaPusSecondTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://3ddd.ru/");

        WebElement button = driver.findElement(By.xpath("//a[@href = '/login']"));
        button.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://3ddd.ru/login");
    }

    @Ignore
    @Test
    public void tatyanaPusThirdTest() throws InterruptedException {

        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://3ddd.ru/");

        WebElement button = driver.findElement(By.xpath("//*[text()='3D Модели']"));
        button.click();
        Thread.sleep(1000);

        WebElement link = driver.findElement(By.xpath("//div[@class='cat not-real-a']"));
        link.click();
        Thread.sleep(1000);

        WebElement checkmark = driver.findElement(By.xpath("//*[@id='collapse-0']/ul/li[1]/div"));
        checkmark.click();
        Thread.sleep(1000);

        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //Find element by xpath and store in variable "Element"
        WebElement element = driver.findElement(By.xpath("//a[@class='sky-btn']"));

        //This will scroll the page till the element is found
        Thread.sleep(3000);
        element.click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://3ddd.ru/3dmodels?cat=dekor&subcat=3d_panel&page=2");
    }

    @Ignore
    @Test
    public void tatyanaPusFourthTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://3ddd.ru/");

        WebElement button = driver.findElement(By.xpath("//*[text()='3D Модели']"));
        button.click();

        Thread.sleep(2000);

        //Instantiate Action Class
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'image' to perform mouse hover
        WebElement menuOption = driver.findElement(By.xpath("//app-models-list/div[1]/div/div[1]/div[1]/a"));
        //Mouse hover 'image'
        actions.moveToElement(menuOption).perform();
        Thread.sleep(3000);
    }

    @Ignore
    @Test
    public void evgenyLukyanenkoRoyal1() throws InterruptedException {

        WebDriver browser = getDriver();
        browser.get("https://www.royal.uk/");

        WebElement button = browser.findElement(By.xpath("//h2[contains(text(), 'Charities and patronages')]"));
        button.click();
        Thread.sleep(1000);

        WebElement dropdown = browser.findElement(By.xpath("//span[contains(@class, 'sod_label')]"));
        dropdown.click();
        Thread.sleep(1000);

        WebElement input = browser.findElement(By.xpath("//*[@id=\"edit-mrf-wrapper\"]/div//span[20]"));
        assertEquals(input.getText(), "Princess Michael of Kent");
    }

    @Ignore
    @Test
    public void evgenyLukyanenkoRoyal2() throws InterruptedException {

        WebDriver browser = getDriver();
        browser.get("https://www.royal.uk/");

        WebElement button = browser.findElement(By.xpath("//h2[contains(text(), 'Charities and patronages')]"));
        button.click();
        Thread.sleep(1000);

        WebElement input = browser.findElement(By.xpath("//span[contains(@class, 'sod_label')]"));
        assertEquals(input.getText(), "All Members of the Royal Family");
    }

    @Test  
    public void aminaB() {

        WebDriver driver = getDriver();
        driver.get("https://www.nba.com/");

        WebElement button = driver.findElement(By.id("nbaMenuButton"));
        button.click();

        WebElement result = driver.findElement(By.id("nbaMenuNBASignIn"));
        Assert.assertEquals(result.getText(), "Sign in to NBA Account");
    }

    @Test
    public void nataliyaPlatonova() throws InterruptedException{

        WebDriver browser = getDriver();
        browser.get("https://www.cds.org/");

        WebElement memberButton = browser.findElement(By.xpath("//*[@id='cm-dropdown1']"));
        memberButton.click();
        Thread.sleep(1000);

        WebElement meetingsButton = browser.findElement(By.xpath("//*[@id='cm-dropdown2']"));
        meetingsButton.click();
        Thread.sleep(1000);

        WebElement classifiedsButton = browser.findElement(By.xpath("//*[@id='cm-dropdown3']"));
        classifiedsButton.click();
        Thread.sleep(1000);

        Assert.assertEquals(memberButton.getText(), "Member Center" );
        Assert.assertEquals(meetingsButton.getText(), "Meetings & Events");
        Assert.assertEquals(classifiedsButton.getText(), "Classifieds");
    }

    @Test
    public void vitaliiTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://www.aerotek.com/en/");

        WebElement button = driver.findElement(By.xpath("(//button[contains(text(), 'SEARCH')])[2]"));
        button.click();
        Thread.sleep(3000);

        WebElement input = driver.findElement(By.xpath("//input[contains(@class, 'ph-a11y-location-box')]"));
        assertEquals(input.getAttribute("placeholder"), "Enter City, State or Zip");
    }

    @Test
    public void arturTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://talentbridge.jobs.net/jobs?utm_medium=email&SiteID=TNALERT_JobsNearYou&utm_source=tn-jobs-near-you&utm_campaign=member-real-time-job-recs");

        WebElement button = driver.findElement(By.xpath("//span[contains(text(),'What job do you want?')]"));
        button.click();
        Thread.sleep(3000);

        WebElement input = driver.findElement(By.xpath("//input[@id='Keywords']"));
        assertEquals(input.getAttribute("placeholder"), "Job title, Skill, Industry");


    }
}
