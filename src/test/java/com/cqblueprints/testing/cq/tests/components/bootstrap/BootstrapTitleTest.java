package com.cqblueprints.testing.cq.tests.components.bootstrap;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapTitleTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "title";
	public static final String TITLE_FIELD_NAME = "./jcr:title";
	public static final String NEW_TITLE = "NewTestTitle";


	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Title", "mainParsys");
	}

	@Test
	public void testTitle() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(TITLE_FIELD_NAME, NEW_TITLE);
		authorPage.confirmDialog();
		authorPage.assertExists(By.xpath("//h1[text()='"+NEW_TITLE+"']"));
	}

	@Test
	public void testPublishValues() throws Exception {
		testTitle();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(By.xpath("//h1[text()='"+NEW_TITLE+"']"));
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
