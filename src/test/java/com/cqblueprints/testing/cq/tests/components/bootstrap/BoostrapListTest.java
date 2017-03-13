package com.cqblueprints.testing.cq.tests.components.bootstrap;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BoostrapListTest extends DefaultBootstrapComponentBase {

	public static final String COMPONENT_CRX_NAME = "list";
	public static final String[] LIST_PATHS = {"/content/geometrixx/en"};
	public static final String[] INVALID_LIST_PATHS = {"invalidpath"};
	public static final String[] VALID_LINKS = {"English"};
	public static final String[] OVERRIDE_TITLE = {"Override Title"};
	

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("List", "mainParsys");
	}

	@Test
	public void testList() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.addTabs(1);
		authorPage.fillInMultipleFields("./pages", LIST_PATHS);
		authorPage.confirmDialog();
		Thread.sleep(1000);
		authorPage.switchToContent();
		for (String link : VALID_LINKS) {
			authorPage.assertLinkText(link);
		}
	}
	
	@Test
	public void testListTitleOverride() throws Exception {
		
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.addTabs(1);
		authorPage.fillInMultipleFields("./pages", LIST_PATHS);
		authorPage.fillInMultipleFields("./titles", OVERRIDE_TITLE);
		authorPage.confirmDialog();
		Thread.sleep(1000);
		authorPage.switchToContent();
		authorPage.assertLinkText(OVERRIDE_TITLE[0]);
	}
	
	@Test
	public void testInvalidLinkList() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.addTabs(1);
		authorPage.fillInMultipleFields("./pages", INVALID_LIST_PATHS);
		authorPage.confirmDialog();
		Thread.sleep(1000);
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//img[contains(@title,'invalid link')]"));
	}

	@Test
	public void testPublishValues() throws Exception {
		testList();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			for (String link : VALID_LINKS) {
				publishPage.assertLinkText(link);
			}
		} finally {
			publishPage.closeDriver();
		}
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deleteComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}


}
