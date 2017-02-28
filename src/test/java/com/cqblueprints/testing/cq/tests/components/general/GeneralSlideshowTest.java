package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralSlideshowTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "slideshow";
	public static final String ASSET_PATH = "/content/dam/projects/outdoors/cover";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Slideshow", "par");
	}

	@Test
	public void testSlideshow() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.waitForRefresh();
		authorPage.dragAssetIntoParsys(ASSET_PATH, "slideshow");
		Thread.sleep(8000);
		authorPage.checkWidthAndHeightOfComponent(COMPONENT_CRX_NAME, "700px", "303px");
	}

	@Test
	public void testPublishValues() throws Exception {
		testSlideshow();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.checkWidthAndHeightOfComponent(COMPONENT_CRX_NAME, "700px", "303px");
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
