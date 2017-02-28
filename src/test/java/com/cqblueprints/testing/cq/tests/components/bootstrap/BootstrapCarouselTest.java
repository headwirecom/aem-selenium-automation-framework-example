package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapCarouselTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "carousel";
	public static final String carouselAsset = "/content/geometrixx/en/products";
	public static final String[] CAROUSEL_IMAGE_OVERRIDE = {"/content/dam/geometrixx/portraits/yolanda_huggins.jpg",
															"/content/dam/geometrixx/portraits/laura_richardson.jpg"};
	public static final String[] IMAGE_TITLE = {"YOLANDA","LAURA"};
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Carousel", "mainParsys");
	}
	
	@Test
	public void editComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(2);
		authorPage.fillInMultipleFields("./images", CAROUSEL_IMAGE_OVERRIDE);
		authorPage.fillInMultipleFields("./titles", IMAGE_TITLE);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[@class='item active']/img[@src='"+CAROUSEL_IMAGE_OVERRIDE[0]+"']"));
		authorPage.assertExists(By.xpath("//div[@class='item']/img[@src='"+CAROUSEL_IMAGE_OVERRIDE[1]+"']"));
		authorPage.assertExists(By.xpath("//div[@class='carousel-caption']/h3[text()='"+IMAGE_TITLE[0]+"']"));
	}
	
	@Test
	public void testPublishValues() throws Exception {
		editComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(By.xpath("//div[@class='item active']/img[@src='"+CAROUSEL_IMAGE_OVERRIDE[0]+"']"));
		publishPage.assertExists(By.xpath("//div[@class='item']/img[@src='"+CAROUSEL_IMAGE_OVERRIDE[1]+"']"));
		publishPage.assertExists(By.xpath("//div[@class='carousel-caption']/h3[text()='"+IMAGE_TITLE[0]+"']"));
		publishPage.clickBy(By.xpath("//a[@class='right carousel-control']"));
		publishPage.assertExists(By.xpath("//div[@class='item active']/img[@src='"+CAROUSEL_IMAGE_OVERRIDE[1]+"']"));
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
