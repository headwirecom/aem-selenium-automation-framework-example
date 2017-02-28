package com.cqblueprints.testing.cq.tests.components.columns;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class ColumnControlTest extends DefaultComponentBase {
	
	@Test
	public void testColumnControl() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Column Control", "par");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@data-path,'/colctrl')]")));
		Assert.assertNotNull("Column component could not be placed", driver.findElements(By.xpath("//div[contains(@data-path,'/colctrl')]")));
		// Page refreshes
		authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("Column Control");
	}
	
	@Test
	public void testColumnControlSightly() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Column Control - Sightly", "par");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@data-path,'/colctrl')]")));
		Assert.assertNotNull("Column Control - Sightly component could not be placed", driver.findElements(By.xpath("//div[contains(@data-path,'/colctrl')]")));
		// Page refreshes
		authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("Column Control - Sightly");
	}
	
}
