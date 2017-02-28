package com.cqblueprints.testing.cq.tests.weretail.components.form;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

public class WeRetailRadioGroup extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Radio Group";
	public static final String COMPONENT_CRX_NAME = "radio";

	private void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS);
	}

	@Test
	public void testDropdownValues() throws Exception {
		String[] dropdownValues = { "testvalue1", "testvalue2"};
		String elementName = "radio-elem-name";

		placeComponent();

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.addTabs(2);
		authorPage.fillInMultipleFields("./options", dropdownValues);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.assertExists(By.name(elementName));
		for (String val : dropdownValues) {
			authorPage.assertExists(By.xpath("//input[@value='"+val+"']"));
		}
	}

	@Test
	public void testPublishDropdownValues() throws Exception {
		String[] dropdownValues = { "testvalue1", "testvalue2"};
		String elementName = "radio-elem-name";

		placeComponent();

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./name", elementName);
		authorPage.addTabs(2);
		authorPage.fillInMultipleFields("./options", dropdownValues);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.activatePage(TEST_PAGE, environment);

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.name(elementName));
			for (String val : dropdownValues) {
				publishPage.assertExists(By.xpath("//select/option[@value='"+val+"']"));
			}
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
