package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapModalTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "modal";
	public static final String MODAL_FIELD_NAME = "./title";
	public static final String MODAL_FIELD_INPUT = "modaltitle";
	public static final String TRIGGER_TEXT_FIELD_NAME = "./triggerText";
	public static final String TRIGGER_TEXT_INPUT = "trigger text";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Modal", "mainParsys");
	}

	@Test
	public void testModal() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(TRIGGER_TEXT_FIELD_NAME, TRIGGER_TEXT_INPUT);
		authorPage.fillInDialogFieldByName(MODAL_FIELD_NAME, MODAL_FIELD_INPUT);
		authorPage.confirmDialog();
		authorPage.refresh();
		authorPage.waitForRefresh();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//button[text()='"+TRIGGER_TEXT_INPUT+"']"));
		authorPage.assertExists(By.xpath("//h4[text()='"+MODAL_FIELD_INPUT+"']"));
	}

	@Test
	public void testModalPublishValues() throws Exception {
		testModal();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.closeDriver();
		authorPage.deactivatePage(TEST_PAGE, environment);
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
