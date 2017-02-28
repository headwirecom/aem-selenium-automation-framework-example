package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralListTest extends DefaultComponentBase {

	public static final String COMPONENT_CRX_NAME = "list";
	public static final String PATH_FIELD_XPATH = "//span[@data-init='pathbrowser']";
	public static final String LIST_PATH = "/content/geometrixx/en";
	public static final String[] VALID_LINKS = {"Products", "Services", "Company", "Events", "Support", "Community"};

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("List", "par");
	}

	@Test
	public void testList() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./parentPage", LIST_PATH);
		//authorPage.followingSiblingInput("Children of", LIST_PATH);
		authorPage.selectDropDown("Display as", "default");
		authorPage.confirmDialog();
		authorPage.switchToContent();
		for (String link : VALID_LINKS) {
			authorPage.checkLinkByText(link);
		}
	}

	@Test
	public void testPublishValues() throws Exception {
		testList();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			for (String link : VALID_LINKS) {
				publishPage.checkLinkByText(link);
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
