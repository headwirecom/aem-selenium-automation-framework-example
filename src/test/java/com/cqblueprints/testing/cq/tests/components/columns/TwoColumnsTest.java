package com.cqblueprints.testing.cq.tests.components.columns;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class TwoColumnsTest extends DefaultComponentBase {
	
	@Test
	public void testTwoColumnControl() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("2 Columns", "par");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@data-path,'/col_break')]")));
		Assert.assertNotNull("2 Column component could not be placed", driver.findElements(By.xpath("//div[contains(@data-path,'/col_break')]")));
		// Page refreshes
		authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("2 Columns");
	}
	
	@Test
	public void testTwoColumnControlSightly() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("2 Columns - Sightly", "par");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@data-path,'/col_break')]")));
		Assert.assertNotNull("2 Column - Sightly component could not be placed", driver.findElements(By.xpath("//div[contains(@data-path,'/col_break')]")));
		// Page refreshes
		authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("2 Columns - Sightly");
	}
	
}
