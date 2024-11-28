package com.resolver.tests;

import com.resolver.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class HomePageTests {
	private WebDriver driver;
	private HomePage homePage;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void navigateToHomePage() throws URISyntaxException {
		Path htmlFile = Paths.get("QE-index.html");
		driver.get(htmlFile.toUri().toString());		
	}

	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test1LoginWithValidCredentials() {
		homePage.enterEmail("test@example.com");
		homePage.enterPassword("password123");
		homePage.clickLogin();
		// Assertions to be added. Not adding any as no url to verify or any user credentials to check. 
	}

	// Added below two tests to verify invalid or null values and data driven approach can be followed if needed. 
	@Test
	public void test1LoginWithInvalidEmail() {
		homePage.enterEmail("test");
		homePage.enterPassword("password123");
		homePage.clickLogin();
		// Verify validation message displayed
		Assert.assertTrue(homePage.getEmailValidationMessage().contains("Please include an '@'"));
	}

	@Test
	public void test1LoginWithNoPassword() {
		homePage.enterEmail("test@example.com");
		homePage.enterPassword(" ");
		homePage.clickLogin();
		// Verify validation message displayed
		Assert.assertTrue(homePage.getPasswordValidationMessage().contains("Please fill out this field."));
	}

	@Test
	public void test2ListGroupValues() {
		// Verify the list count
		Assert.assertEquals(homePage.getListItemCount(), 3);
		// Verify second list item value
		Assert.assertTrue(homePage.getListItemText(1).contains("List Item 2"), "Test does not match");
		// Verify second list item badge value
		Assert.assertEquals(homePage.getListItemBadge(1), "6");
	}
	
	// Dropdown selections and list item values can be parameterized for reusability.

	@Test
	public void test3DropdownSelection() {
		// Verify "Option 1" is default
		Assert.assertEquals(homePage.getDropdownText(), "Option 1");
		homePage.selectDropdownOption("Option 3");
		// Verify "Option 3" is displayed 
		Assert.assertEquals(homePage.getDropdownText(), "Option 3");
	}

	@Test
	public void test4ButtonStates() {
		// Verify that the first button is enabled 
		Assert.assertTrue(homePage.isFirstButtonEnabled());
		// Verify that the second button is disabled
		Assert.assertTrue(homePage.isSecondButtonDisabled());
	}
	
	// Timeouts for such elements be configurable if required.
	// Can refactor to fail the test if the button takes longer than 15 seconds to display, or can increase the wait time.

	@Test
	public void test5ButtonWaitAndClick() {
		// Wait for the button to be displayed 
		Assert.assertTrue(homePage.waitForTest5ButtonToAppear().isDisplayed());
		// Then click it
		homePage.clickTest5Button();
		// Verify Success message displayed
		Assert.assertTrue(homePage.isSuccessAlertDisplayed());
		// Verify button is disabled
		Assert.assertTrue(homePage.isTest5ButtonDisabled());
	}

	// Table lookup method can support dynamic row and column identification (e.g., by header text).
	@Test
	public void test6TableCellValue() {
		String cellValue = homePage.getTableCellValue(2, 2);
		Assert.assertEquals(cellValue, "Ventosanzap");
	}
}
