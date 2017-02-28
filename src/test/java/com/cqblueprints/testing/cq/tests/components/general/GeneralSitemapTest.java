package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralSitemapTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "sitemap";
	public static final String SITEMAP_ROOT_PATH ="/content/geometrixx/en/products";
	public static final String SITEMAP_PATH_FIELD_NAME = "./rootPath";

	public static final String[] ROOTED_SITEMAP_LINKS = {"Products","Triangle","Overview","Features","Square","Circle","Mandelbrot Set"};

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Sitemap", "par");
	}

	@Test
	public void testSitemap() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		for (String link : ROOTED_SITEMAP_LINKS) {
			authorPage.assertLinkText(link);
		}

	}

	@Test
	public void testPublishValues() throws Exception {
		testSitemap();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			for (String link : ROOTED_SITEMAP_LINKS) {
				publishPage.assertLinkText(link);
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
