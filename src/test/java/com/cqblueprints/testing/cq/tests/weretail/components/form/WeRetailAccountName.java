package com.cqblueprints.testing.cq.tests.weretail.components.form;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.HashMap;

public class WeRetailAccountName extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Account Name";
	public static final String COMPONENT_CRX_NAME = "accountname";

	HashMap<String, String> fields = new HashMap<String, String>(){{
		put("Element Name", "./name");
		put("Title", "./jcr:title");
		put("Hide Title", "./hideTitle");
		put("Description", "./jcr:description");
		put("Required", "./required");
		put("Required Message", "./requiredMessage");
		put("Constraint Message", "./constraintMessage");
	}};

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS);
	}

	@Test
	public void testInputValues() throws Exception {
		placeComponent();

		String elementName = "newelementname";
		String title = "Account Name Title";
		String description = "Account Name Description";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get("Element Name"), elementName);
		authorPage.fillInDialogFieldByName(fields.get("Title"), title);
		authorPage.fillInDialogFieldByName(fields.get("Description"), description);
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.assertExists(By.name(elementName));
		authorPage.assertText(By.xpath("//label[@for='new_form_"+elementName+"']"), title);
		authorPage.assertText(By.xpath("//div[@class='form_row_description']/span"), description);
	}

	@Test
	public void testHideTitle() throws Exception {
		placeComponent();

		String elementName = "newelementname";
		String title = "Account Name Title";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get("Element Name"), elementName);
		authorPage.fillInDialogFieldByName(fields.get("Title"), title);
		authorPage.clickBy(By.name("./hideTitle"));
		authorPage.confirmDialog();
		authorPage.switchToContent();

		authorPage.assertExists(By.name(elementName));
		authorPage.assertVisibility(By.xpath("//label[@for='new_form_"+elementName+"']"), false);
	}

	@Test
	public void testPublishValues() throws Exception {
		placeComponent();

		String elementName = "newelementname";
		String title = "Account Name Title";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get("Element Name"), elementName);
		authorPage.fillInDialogFieldByName(fields.get("Title"), title);
		authorPage.clickBy(By.name("./hideTitle"));
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.activatePage(TEST_PAGE, environment);

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.name(elementName));
			publishPage.assertVisibility(By.xpath("//label[@for='new_form_"+elementName+"']"), false);
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
