package lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

/**
 * Selenium Web Driver Tests against <a href="https://the-internet.herokuapp.com/">The Internet</a>
 */
public class WebDriver_Example {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Pre-Configured Service Options
        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogOutput(System.out) // Can also be a log file
                .withLogLevel(FirefoxDriverLogLevel.DEBUG) // Logging Levels
                .withTruncatedLogs(false) // Indicates whether to truncate long lines in the log.
                        // Log lines are truncated by default; setting "false" removes truncation
                .build();

        driver = new FirefoxDriver(service);
    }

    @AfterTest
    public void teardown(){
        driver.close();
    }


    /**
     * Helper method to wait on a given page for 5 seconds.
     */
    public void page_wait() { page_wait(60); }
    /**
     * Helper method to wait on a given page for a set amount of time in seconds.
     * @param seconds
     */
    public void page_wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(
                seconds,
                TimeUnit.SECONDS);
    }

    /**
     * Helper method to load the page for the web driver.
     * @param url Web page's url
     */
    public void loadPage(String url) { loadPage(url, "The Internet"); }
    /**
     * Helper method to load the page for the web driver.
     * @param url Web page's url
     * @param page_title Web page's title
     */
    public void loadPage(String url, String page_title){
        driver.get(url);

        // Checking if loaded url is still the passed in url.
        Assert.assertEquals(url, driver.getCurrentUrl(),
                String.format("Driver's current url, %s, is missed matched with provided url, %s", url, driver.getCurrentUrl()));

        /* Turning this assert off for now.
        // Checking web page's title.
        Assert.assertTrue(
                driver.getTitle().contains(page_title),
                String.format("Site's page title doesn't contain %s", page_title));

         */
    }

    /**
     * Runs against web page
     * <a href="https://the-internet.herokuapp.com/checkboxes"/>Check Boxes</a>
     * to test clicking page features.
     */
    @Test
    public void checkboxClicker() throws InterruptedException {
        // Loading web driver with checkbox clicker page.
        loadPage("https://the-internet.herokuapp.com/checkboxes");

        // Using Xpath to find web element.
        WebElement box1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        WebElement box2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));

        // Assert message if the checkbox was selected or not.
        String msg_selected = "Checkbox %d was selected.";
        String msg_not_selected = "Checkbox %d was not selected.";

        // Page should load with Checkbox 2 being pre-selected.
        Assert.assertFalse(box1.isSelected(), String.format(msg_selected, 1));
        Assert.assertTrue(box2.isSelected(), String.format(msg_not_selected, 2));

        // Un-selecting all check boxes.
        box2.click();
        Assert.assertFalse(box1.isSelected(), String.format(msg_selected, 1));
        Assert.assertFalse(box2.isSelected(), String.format(msg_selected, 2));

        // Selecting the 1st checkbox only.
        box1.click();
        Assert.assertTrue(box1.isSelected(), String.format(msg_not_selected, 1));
        Assert.assertFalse(box2.isSelected(), String.format(msg_selected, 2));

        // Selecting the 2nd checkbox, both should be selected.
        box2.click();
        Assert.assertTrue(box1.isSelected(), String.format(msg_not_selected, 1));
        Assert.assertTrue(box2.isSelected(), String.format(msg_not_selected, 2));
    }

    /**
     * Runs against web page
     * <a href="https://the-internet.herokuapp.com/add_remove_elements/">Add Remove Elements</a>
     * to click dynamic generated buttons.
     */
    @Test
    public void bottonClicker() {
        loadPage("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement btnAdd = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        // Clicking the button 5 times to populate page with buttons to delete.
        for(int i = 1; i <= 5; i++) {
            btnAdd.click();
        }

        // Clicking the new delete buttons on the page.  Clearing button stack in LIFO style.
        for(int i = 5; i > 0; i--) {
            WebElement btnDelete = driver.findElement(
                    By.xpath(String.format(
                            "//*[@id=\"elements\"]/button[%d]",
                            i)));

            btnDelete.click();
        }
    }

    /**
     * Runs against web page <a href="https://the-internet.herokuapp.com/key_presses">Key Presses</a>
     * to insert strings into a text box.
     */
    @Test
    public void inputText(){
        // Content of the text box.
        String content = "";

        loadPage("https://the-internet.herokuapp.com/key_presses");
        WebElement input = driver.findElement(By.id("target"));

        // Textbox should be clear by default on page load.
        content = input.getAttribute("value");
        Assert.assertTrue(content.isEmpty(), "Textbox is not empty!");

        // Sending Hello World into text box.
        String helloWorld = "Hello World!";
        input.sendKeys(helloWorld);
        content = input.getAttribute("value");
        Assert.assertEquals(content, helloWorld, "Text box doesn't contain: " + helloWorld);
    }


    /**
     * Runs a selenium action builder against <a href="https://the-internet.herokuapp.com/infinite_scroll">Infinite Scroll</a>
     */
    @Test
    public void actionBuilder() {
        loadPage("https://the-internet.herokuapp.com/infinite_scroll");

        // Looping the page for a while with multiple actions.
        for(int i = 0; i < 10; i++) {
            new Actions(driver)
                    .scrollByAmount(0, 100)
                    .perform();
        }
    }
}
