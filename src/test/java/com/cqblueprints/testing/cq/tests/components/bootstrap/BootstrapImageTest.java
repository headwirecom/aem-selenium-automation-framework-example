package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapImageTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "image";
	public static final String IMAGE_TITLE_FIELD_NAME = "./jcr:title";
	public static final String IMAGE_ALT_TITLE_FIELD_NAME = "./alt";
	public static final String IMAGE_LINK_FIELD_NAME = "./linkURL";
	public static final String IMAGE_DESCRIPTION_FIELD_NAME ="./jcr:description";
	public static final String IMAGE_WIDTH_FIELD_NAME = "./width";
	public static final String IMAGE_HEIGHT_FIELD_NAME = "./height";

	public static final String ASSET_PATH = "/content/dam/projects/outdoors/cover";
	public static final String IMAGE_TITLE = "Image title";
	public static final String IMAGE_ALT_TITLE = "Image Alt Title";
	public static final String IMAGE_DESCRIPTION = "Unique Image Description";


	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Image", "mainParsys");
	}

	@Test
	public void testImageComponentMetaData() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(IMAGE_TITLE_FIELD_NAME, IMAGE_TITLE);
		authorPage.fillInDialogFieldByName(IMAGE_ALT_TITLE_FIELD_NAME, IMAGE_ALT_TITLE);
		authorPage.fillInDialogFieldByName(IMAGE_DESCRIPTION_FIELD_NAME, IMAGE_DESCRIPTION);
		authorPage.confirmDialog();
		authorPage.selectSidePanelTab("Assets");
		authorPage.dragAssetIntoParsys(ASSET_PATH, COMPONENT_CRX_NAME);
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//div[@class='cq-dd-image']/img[@src='"+ASSET_PATH+"']"));
	}

	@Test
	public void testImagePublishValues() throws Exception {
		testImageComponentMetaData();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(By.xpath("//div[@class='cq-dd-image']/img[@src='"+ASSET_PATH+"']"));
		publishPage.closeDriver();
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("Image");

	}

}
