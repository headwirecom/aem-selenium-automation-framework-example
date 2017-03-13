package com.cqblueprints.testing.cq.tests.components.general;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import org.junit.Test;
import org.openqa.selenium.By;

public class GeneralTitleTest extends RetailBaseTest {
	public static final String COMPONENT_CRX_NAME = "title";
	public static final String TITLE_FIELD_NAME = "./jcr:title";
	public static final String NEW_TITLE = "NewTestTitle";
	public static final By TITLE_BY = By.xpath("//div[contains(@class,'title')]/h1");

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Title", TARGET_PARSYS);
	}

	@Test
	public void testTitle() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		authorPage.assertText(TITLE_BY, TEST_PAGE_NAME);
		authorPage.switchToDefaultContent();
		authorPage.editComponent(COMPONENT_CRX_NAME);
//		authorPage.fillInDialogFieldByName(TITLE_FIELD_NAME, NEW_TITLE);
		authorPage.switchToContent();
		authorPage.type(NEW_TITLE);
		authorPage.closeSuggestions();
		authorPage.assertText(TITLE_BY, NEW_TITLE+TEST_PAGE_NAME);
	}

	@Test
	public void testPublishValues() throws Exception {
		testTitle();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertText(TITLE_BY, NEW_TITLE);
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
