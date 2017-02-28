package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapHeaderTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "header";
	public static final By HEADER_BY = By.xpath("//div[contains(@class,'header')]");

//	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Header", "par");
	}

//	@Test
	public void testHeader() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.assertExists(HEADER_BY);
	}

//	@Test
	public void testPublishValues() throws Exception {
		testHeader();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(HEADER_BY);
		publishPage.closeDriver();
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

//	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
