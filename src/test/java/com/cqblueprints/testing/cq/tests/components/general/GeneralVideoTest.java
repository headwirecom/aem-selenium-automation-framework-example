package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralVideoTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_PATH = "video";
	public static final String ASSET_PATH = "/content/dam/geometrixx-outdoors/products/equipment/Sparrow.mov";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Video", "par");
	}

	@Test
	public void testVideo() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.selectSidePanelTab("Assets");
		authorPage.selectAssetFinderDropdown("Videos");
		authorPage.dragAssetIntoParsys(ASSET_PATH, "video");
//		authorPage.activatePage(TEST_PAGE, environment);
		
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_PATH);
	}
}
