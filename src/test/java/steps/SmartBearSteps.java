package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.ast.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.SmartBearBasePage;
import pages.SmartBearOrdersPage;
import pages.SmartBearWebOrdersPage;
import utils.Driver;

import java.util.List;

public class SmartBearSteps {

    WebDriver driver;
    SmartBearBasePage smartBearBasePage;
    SmartBearWebOrdersPage smartBearWebOrdersPage;
    SmartBearOrdersPage smartBearOrdersPage;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        smartBearBasePage = new SmartBearBasePage();
        smartBearWebOrdersPage = new SmartBearWebOrdersPage();
        smartBearOrdersPage = new SmartBearOrdersPage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUserName(String username) {
        smartBearBasePage.usernameField.sendKeys(username);
    }

    @When("user enters password as {string}")
    public void userEntersPassword(String password) {
        smartBearBasePage.passwordField.sendKeys(password);
    }
    @When("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearBasePage.loginButton.click();
    }
    @Then("user should see {string} message")
    public void validateErrorMessage(String errorMessage) {
        Assert.assertEquals(errorMessage, smartBearBasePage.loginErrorMessage.getText());
    }
    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String urlResult) {
        Assert.assertEquals(urlResult, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(List<String> expectedItems, List<WebElement> actualItems) {
        for (int i = 0; i < expectedItems.size(); i++) {
            Assert.assertEquals(expectedItems.get(i), actualItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttons) {
        smartBearWebOrdersPage.checkAllButton.click();
        smartBearWebOrdersPage.unCheckAllButton.click();

        switch(buttons) {
            case "Check All":
                smartBearWebOrdersPage.checkAllButton.click();
                break;
            case "Uncheck All":
                smartBearWebOrdersPage.unCheckAllButton.click();
                break;
            case "Process":
                smartBearOrdersPage.processButton.click();
            case "Delete Selected":
                smartBearOrdersPage.deleteButton.click();
            default:
                throw new NotFoundException("The heading text is not defined!");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for(int i = 0; i < smartBearWebOrdersPage.checkBox.size(); i++)
            Assert.assertTrue(smartBearWebOrdersPage.checkBox.get(i).isSelected());
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {

        for(int i = 0; i < smartBearWebOrdersPage.checkBox.size(); i++)
            Assert.assertFalse(smartBearWebOrdersPage.checkBox.get(i).isSelected());
    }}