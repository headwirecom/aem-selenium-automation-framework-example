package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class BootstrapBreadcrumbTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "breadcrumb";
	public static final String[] VALID_LINKS = {"Home", TEST_PAGE_NAME};

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Breadcrumb", "mainParsys");
	}

	@Test
	public void testBreadcrumb() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./path", TEST_PAGE_PARENT+TEST_PAGE_NAME);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		for (String link : VALID_LINKS) {
			authorPage.assertLinkText(link);
		}
	}
	
	@Test
	public void testBreadcrumbDepth() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName("./path", TEST_PAGE_PARENT+TEST_PAGE_NAME);
		authorPage.increment(1, 0);
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.assertLinkText(VALID_LINKS[1]);
	}

	@Test
	public void testPublishValues() throws Exception {
		testBreadcrumb();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		for (String link : VALID_LINKS) {
			publishPage.assertLinkText(link);
		}
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
