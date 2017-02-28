package com.cqblueprints.testing.cq.tests.components.general;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralFlashTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "flash";
	public static final String ASSET_PATH = "/content/dam/geometrixx/movies/shape_summit.swf";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(
				driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Flash", "par");
	}

	@Test
	public void testFlash() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(
				driver, wait, environment.getVersion());
		authorPage.selectSidePanelTab("Assets");
		authorPage.selectAssetFinderDropdown("Videos");
		authorPage.dragAssetIntoParsys(ASSET_PATH, COMPONENT_CRX_NAME);
		authorPage.waitForRefresh();
		authorPage.assertExists(By.xpath("//object[@data='" + ASSET_PATH + "']"));
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(
				driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
