package com.cqblueprints.testing.cq.tests.weretail.components;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

public class WeRetailTitle extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Title";
	public static final String COMPONENT_CRX_NAME = "title";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS);
	}

	@Test
	public void testTitle() throws Exception {
		placeComponent();
		String newTitle = "New Title";

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToDefaultContent();
		authorPage.switchToContent();
		authorPage.selectInlineEditor(newTitle);
		authorPage.switchToDefaultContent();
		authorPage.closeInlineEditor();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath("//h1[text()='"+newTitle+"']"));
	}

	@Test
	public void testPublishValues() throws Exception {
		String inputText = "Text For Input";

		placeComponent();

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToDefaultContent();
		authorPage.switchToContent();
		authorPage.selectInlineEditor(inputText);
		authorPage.switchToDefaultContent();
		authorPage.closeInlineEditor();
		authorPage.switchToContent();

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.xpath("//p[text()='"+inputText+"']"));
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
