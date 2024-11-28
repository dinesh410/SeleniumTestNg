package com.resolver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

	@FindBy(id = "inputEmail")
	private WebElement emailField;

	@FindBy(id = "inputPassword")
	private WebElement passwordField;

	@FindBy(css = "button[type='submit']")
	private WebElement loginButton;

	@FindBy(css = "#test-2-div .list-group-item")
	private List<WebElement> listItems;

	@FindBy(id = "dropdownMenuButton")
	private WebElement dropdownButton;

	@FindBy(css = "#test-4-div .btn-primary")
	private WebElement tesst4FirstButton;

	@FindBy(css = "#test-4-div .btn-secondary")
	private WebElement test4SecondButton;

	@FindBy(id = "test5-button")
	private WebElement test5Button;

	@FindBy(id = "test5-alert")
	private WebElement test5Alert;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Login methods
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public String getEmailValidationMessage() {
		return emailField.getAttribute("validationMessage");
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public String getPasswordValidationMessage() {
		return passwordField.getAttribute("validationMessage");
	}

	public void clickLogin() {
		loginButton.click();
	}

	// List group methods
	public int getListItemCount() {
		return listItems.size();
	}

	public String getListItemText(int index) {
		return listItems.get(index).getText();
	}

	public String getListItemBadge(int index) {
		return listItems.get(index).findElement(By.cssSelector(".badge")).getText();
	}

	// Dropdown methods
	public String getDropdownText() {
		return dropdownButton.getText();
	}

	public void selectDropdownOption(String optionText) {
		dropdownButton.click();
		driver.findElement(By.xpath("//a[text()='" + optionText + "']")).click();
	}

	public boolean isFirstButtonEnabled() {
		return tesst4FirstButton.isEnabled();
	}

	public boolean isSecondButtonDisabled() {
		return !test4SecondButton.isEnabled();
	}

	public WebElement waitForTest5ButtonToAppear() {
		//  Added 15 seconds wait time 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// Wait until the button is visible
		WebElement dynamicButton = wait.until(ExpectedConditions.visibilityOf(test5Button));
		return dynamicButton;
	}

	public void clickTest5Button() {
		test5Button.click();
	}

	public boolean isSuccessAlertDisplayed() {
		return test5Alert.isDisplayed();
	}

	public boolean isTest5ButtonDisabled() {
		return !test5Button.isEnabled();
	}

	// Get table cell value
	public String getTableCellValue(int row, int col) {
		return driver.findElement(By.xpath("//table/tbody/tr[" + (row + 1) + "]/td[" + (col + 1) + "]")).getText();
	}
}
