package com.cqblueprints.testing.cq.tests.components.bootstrap;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapNavigationTest extends DefaultBootstrapComponentBase {

	public static final String COMPONENT_CRX_NAME = "navigation";
	public static final String LIST_PATH = "/content/geometrixx/en";
	public static final String[] VALID_LINKS = {"Page Navigation", TEST_PAGE_NAME};
	public static final By rootNavSelect = By.name("./includeRoot");

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Navigation", "mainParsys");
	}

	@Test
	public void testBootstrapNavigation() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.clickBy(rootNavSelect);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		for (String link : VALID_LINKS) {
			authorPage.assertLinkText(link);
		}
	}
	
	@Test
	public void testPublishValues() throws Exception {
		testBootstrapNavigation();
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
