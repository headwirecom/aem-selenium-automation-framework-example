package com.cqblueprints.testing.cq.tests.weretail.components.form;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

public class WeRetailText extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Text Field";
	public static final String COMPONENT_CRX_NAME = "text";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS);
	}

	@Test
	public void testDefaultText() throws Exception {
		placeComponent();

		String elementName = "text-element-name";
		String defaultText = "Default Text For Input";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.selectDialogTab("Initial Values");
		authorPage.fillInDialogFieldByName("./defaultValue", defaultText);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.assertExists(By.name(elementName));
		authorPage.assertExists(By.xpath("//input[@value='"+defaultText+"']"));
	}

	@Test
	public void testPublishValues() throws Exception {
		placeComponent();

		String elementName = "text-element-name";
		String defaultText = "Default Text For Input";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.selectDialogTab("Initial Values");
		authorPage.fillInDialogFieldByName("./defaultValue", defaultText);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.name(elementName));
			publishPage.assertExists(By.xpath("//input[@value='"+defaultText+"']"));
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
