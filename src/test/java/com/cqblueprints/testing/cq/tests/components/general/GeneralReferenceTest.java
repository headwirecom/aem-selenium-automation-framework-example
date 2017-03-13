package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralReferenceTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "reference";
	public static final String REFERENCE_PATH_FIELD_NAME = "./path";
	public static final String REFERENCE_PATH = "/content/geometrixx/en/products/jcr:content/par/title";
	public static final By REFERENCE_BY = By.xpath("//div[@class='title']");
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Reference", "par");
	}

	@Test
	public void testReference() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(REFERENCE_PATH_FIELD_NAME, REFERENCE_PATH);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(REFERENCE_BY);
	}

	@Test
	public void testPublishValues() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(REFERENCE_BY);
		} finally {
			publishPage.closeDriver();
		}
		authorPage.deactivatePage(TEST_PAGE, environment);
	}
	
	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
