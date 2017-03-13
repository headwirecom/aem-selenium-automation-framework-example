package com.cqblueprints.testing.cq.tests.components.bootstrap;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.Constants.MouseAction;
import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapAlertsTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "alerts";
	public static final String TEST_INPUT = "firsttestalert";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Alerts", "mainParsys");
	}

	@Test
	public void testAlert() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.type(TEST_INPUT);
		authorPage.switchToDefaultContent();
		authorPage.closeInlineEditor();
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[contains(@class,'alert')]/p[text()='"+TEST_INPUT+"']"));
	}
	
	@Test
	public void testAlertDismiss() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME, MouseAction.DOUBLECLICK);
		authorPage.clickBy(By.name("./alertDismissButton"));
		authorPage.confirmDialog();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.type(TEST_INPUT);
		authorPage.switchToDefaultContent();
		authorPage.closeInlineEditor();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[contains(@class,'alert')]/p[text()='"+TEST_INPUT+"']"));
		authorPage.assertExists(By.className("close"));
	}

	@Test
	public void testPublishValues() throws Exception {
		testAlert();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		authorPage.assertExists(By.xpath("//div[contains(@class,'alert')]/p[text()='"+TEST_INPUT+"']"));
		authorPage.assertExists(By.className("close"));
		publishPage.closeDriver();
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
