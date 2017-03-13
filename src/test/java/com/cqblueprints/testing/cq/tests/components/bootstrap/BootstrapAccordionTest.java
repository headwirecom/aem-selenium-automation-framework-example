package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapAccordionTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "accordion";
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Accordion", "mainParsys");
	}
	
	@Test
	public void editComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(2);
		authorPage = authorPage.fillInMultipleFields("./tabs", "accordiontab");
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//h4[@class='panel-title][2]"));
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
		editComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(By.xpath("//h4[@class='panel-title]"));
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
