package com.cqblueprints.testing.cq.tests.components.bootstrap;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapTextTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "text";
	public static final String TEXT_INPUT = "This is test text.";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Text", "par");
	}

	@Test
	public void testText() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToContent();
		authorPage.type(TEXT_INPUT);
		authorPage.switchToDefaultContent();
		authorPage.closeInlineEditor();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//p[text()='"+TEXT_INPUT+"']"));
	}

	@Test
	public void testPublishValues() throws Exception {
		testText();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(By.xpath("//p[text()='"+TEXT_INPUT+"']"));
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
