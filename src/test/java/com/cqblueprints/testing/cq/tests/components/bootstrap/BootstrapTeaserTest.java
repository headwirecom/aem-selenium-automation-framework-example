package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapTeaserTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "teaser";
	public static final String[] TEASER_PATH = {"/content/geometrixx/en"};
	public static final String PAGE_INPUT_NAME = "./pages";
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());


		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Teaser", "mainParsys");
	}
	
	@Test
	public void testThreeAcross() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(1);
		authorPage.fillInMultipleFields(PAGE_INPUT_NAME, TEASER_PATH);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[@class='caption']/h3[text()='English']"));
		authorPage.assertLinkText("View More");
		authorPage.assertExists(By.xpath("//div[@class='col-md-4']"));
	}
	
	@Test
	public void testFourAcross() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(1);
		authorPage.fillInMultipleFields(PAGE_INPUT_NAME, TEASER_PATH);
		authorPage.selectDropDown("Teaser Pattern", "4");
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[@class='caption']/h3[text()='English']"));
		authorPage.assertLinkText("View More");
		authorPage.assertExists(By.xpath("//div[@class='col-md-3']"));
	}
	
	@Test
	public void testFullColumn() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(1);
		authorPage.fillInMultipleFields(PAGE_INPUT_NAME, TEASER_PATH);
		authorPage.selectDropDown("Teaser Pattern", "1");
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[@class='caption']/h3[text()='English']"));
		authorPage.assertLinkText("View More");
		authorPage.assertExists(By.xpath("//div[@class='col-md-12']"));
	}
	
	@Test
	public void testPublishValues() throws Exception {
		testThreeAcross();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		authorPage.assertExists(By.xpath("//div[@class='caption']/h3[text()='English']"));
		authorPage.assertLinkText("View More");
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
