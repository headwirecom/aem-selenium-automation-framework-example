package com.cqblueprints.testing.cq.tests.weretail.components.form;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

public class WeRetailImageButton extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Image Button";
	public static final String COMPONENT_CRX_NAME = "imagebutton";

	private void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS);
	}

	@Test
	public void testDropdownValues() throws Exception {
		String elementName = "image-elem-name";
		String buttonTitle = "image button title";
		String buttonDescription = "image button description";
		String imagePath = "/content/we-retail/ca/en/products/men/shirts/eton-short-sleeve-shirt";

		placeComponent();

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.fillInDialogFieldByName("./jcr:title", buttonTitle);
		authorPage.fillInDialogFieldByName("./jcr:description", buttonDescription);
		authorPage.fillInDialogFieldByName("./src", imagePath);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.assertExists(By.name(elementName));
		authorPage.assertExists(By.xpath("//label[text()='"+buttonTitle+"']"));
		authorPage.assertExists(By.xpath("//span[text()='"+buttonDescription+"']"));
		authorPage.assertExists(By.xpath("//input[@src='"+imagePath+"']"));
	}

	@Test
	public void testPublishDropdownValues() throws Exception {
		String elementName = "image-elem-name";
		String buttonTitle = "image button title";
		String buttonDescription = "image button description";
		String imagePath = "/content/we-retail/ca/en/products/men/shirts/eton-short-sleeve-shirt";

		placeComponent();

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.fillInDialogFieldByName("./jcr:title", buttonTitle);
		authorPage.fillInDialogFieldByName("./jcr:description", buttonDescription);
		authorPage.fillInDialogFieldByName("./src", imagePath);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.activatePage(TEST_PAGE, environment);

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.name(elementName));
			publishPage.assertExists(By.xpath("//label[text()='"+buttonTitle+"']"));
			publishPage.assertExists(By.xpath("//span[text()='"+buttonDescription+"']"));
			publishPage.assertExists(By.xpath("//input[@src='"+imagePath+"']"));
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
