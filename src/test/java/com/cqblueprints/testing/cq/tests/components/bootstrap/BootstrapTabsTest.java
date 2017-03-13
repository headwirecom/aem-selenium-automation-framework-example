package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapTabsTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "tabs";
	public static final String TAB_PREFIX = "tab";
	public static final int NUMBER_OF_TABS = 2;
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Tabs", "mainParsys");
	}
	
	@Test
	public void testTabs() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(NUMBER_OF_TABS);
		authorPage = authorPage.fillInMultipleFields("./tabs", TAB_PREFIX);
		authorPage.confirmDialog();
		authorPage.refresh();
		authorPage.waitForRefresh();
		authorPage.switchToContent();
		for (int i=0; i<NUMBER_OF_TABS; i++) {
			authorPage.assertLinkText(TAB_PREFIX+i);
		}
	}
	
	@Test
	public void testEmptyInput() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(2);
		authorPage.confirmDialog();
		authorPage.assertExists(By.xpath("//span[@data-init='quicktip']"));
	}
	
	@Test
	public void testPublishValues() throws Exception {
		testTabs();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		for (int i=1; i<=NUMBER_OF_TABS;i++) {
			publishPage.assertLinkText(TAB_PREFIX+i);
		}
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
