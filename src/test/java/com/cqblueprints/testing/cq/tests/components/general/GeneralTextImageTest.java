package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.Constants.MouseAction;
import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralTextImageTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "textimage";
	public static final String IMAGE_TITLE_FIELD_NAME = "./image/jcr:title";
	public static final String IMAGE_ALT_TITLE_FIELD_NAME = "./image/alt";
	public static final String IMAGE_LINK_FIELD_NAME = "./image/linkURL";
	public static final String IMAGE_DESCRIPTION_FIELD_NAME ="./image/jcr:description";
	public static final String IMAGE_WIDTH_FIELD_NAME = "./image/width";
	public static final String IMAGE_HEIGHT_FIELD_NAME = "./image/height";

	public static final String ASSET_PATH = "/content/dam/projects/outdoors/cover";
	public static final String IMAGE_TITLE = "Image title";
	public static final String IMAGE_ALT_TITLE = "Image Alt Title";
	public static final String IMAGE_DESCRIPTION = "Unique Image Description";
	public static final By DESCRIPTION_BY = By.xpath("//small[text()='"+IMAGE_DESCRIPTION+"']");

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Text & Image", "par");
	}

	@Test
	public void testTextImage() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME, MouseAction.DOUBLECLICK);
		authorPage.selectDialogTab("Image Properties");
		authorPage.fillInDialogFieldByName(IMAGE_TITLE_FIELD_NAME, IMAGE_TITLE);
		authorPage.fillInDialogFieldByName(IMAGE_ALT_TITLE_FIELD_NAME, IMAGE_ALT_TITLE);
		authorPage.fillInDialogFieldByName(IMAGE_DESCRIPTION_FIELD_NAME, IMAGE_DESCRIPTION);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertExists(DESCRIPTION_BY);
		authorPage.switchToDefaultContent();
		authorPage.selectSidePanelTab("Assets");
		authorPage.dragAssetIntoParsys(ASSET_PATH, "textimage");
	}

	@Test
	public void testPublishValues() throws Exception {
		testTextImage();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(DESCRIPTION_BY);
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
