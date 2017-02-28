package com.cqblueprints.testing.cq.tests.components.general;

import static com.cqblueprints.testing.cq.base.BaseActions.ACTIONS;
import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralImageTest extends DefaultComponentBase {
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
		authorPage.dragComponentIntoParsys("Image", "par");
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
		Assert.assertNotNull("Description not found", driver.findElements(By.xpath("//small[text()='"+IMAGE_DESCRIPTION+"']")));
		authorPage.selectSidePanelTab("Assets");
		authorPage.dragAssetIntoParsys(ASSET_PATH, COMPONENT_CRX_NAME);
		//Assert.assertTrue("Image not found on page", driver.getPageSource().contains("imageAsset: "+imagePath));

	}

	@Test
	public void testImagePublishValues() throws Exception {
		testImageComponentMetaData();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.validateImageAttribute("title", IMAGE_TITLE);
			publishPage.validateImageAttribute("alt", IMAGE_ALT_TITLE);
			publishPage.validateContentByTag(By.tagName("small"), IMAGE_DESCRIPTION);
		} finally {
			publishPage.closeDriver();
		}
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent("Image");

	}

	//@Test
	public void testDragImage() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Assets");
		WebElement imageCard = driver.findElement(By.xpath("//article[@data-path = '/content/dam/projects/outdoors/cover']"));
		WebElement imageParsys = driver.findElement(By.xpath("//div[@data-asset-id = 'image']"));
		ACTIONS.dragDrop(driver, wait, imageCard, imageParsys);
	}
}
