package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralDownloadTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "download";
	public static final String DESCRIPTION_FIELD_NAME = "./jcr:description";
	public static final String DESCRIPTION_TEXT = "Sample description for download component";

	public static final String ASSET_PATH = "/content/dam/projects/outdoors/cover";
	public static final String DOWNLOAD_LINK_XPATH = "//a[@href='"+ASSET_PATH+"']"; 
	public static final String DESCRIPTION_XPATH = "//small[text()='"+DESCRIPTION_TEXT+"']";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Download", "par");
	}

	@Test
	public void testDownload() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.selectSidePanelTab("Assets");
		authorPage.dragAssetIntoParsys(ASSET_PATH, COMPONENT_CRX_NAME);
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(DESCRIPTION_FIELD_NAME, DESCRIPTION_TEXT);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath(DOWNLOAD_LINK_XPATH));
		authorPage.assertExists(By.xpath(DESCRIPTION_XPATH));
	}

	@Test
	public void testPublishValues() throws Exception {
		testDownload();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.xpath(DOWNLOAD_LINK_XPATH));
			publishPage.assertExists(By.xpath(DESCRIPTION_XPATH));
		} finally {
			publishPage.closeDriver();
		}
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deletComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
