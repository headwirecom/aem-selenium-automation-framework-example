package com.cqblueprints.testing.cq.tests.components.general;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import org.junit.Test;

public class GeneralTextTest extends RetailBaseTest {
	public static final String COMPONENT_CRX_NAME = "text";
	public static final String TEXT_INPUT = "This is test text.";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Text", TARGET_PARSYS);
	}

	@Test
	public void testText() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editText(COMPONENT_CRX_NAME);
		authorPage.type(TEXT_INPUT);
	}

	@Test
	public void testPublishValues() throws Exception {
		testText();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {

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
