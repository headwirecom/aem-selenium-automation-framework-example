package com.cqblueprints.testing.cq.tests.components.bootstrap;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.pageobjects.SiteAdminPage;

public class BootstrapFooterTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "footer";
	public static final By FOOTER_BY = By.xpath("//div[contains(@class,'"+COMPONENT_CRX_NAME+"')]");

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Footer", "mainParsys");
	}

	@Test
	public void testFooterLinks() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent("footer");
		authorPage.switchToOldDialogIframe();
		String rootPage = authorPage.getTextFromInput(By.name("./navRoot"));

		driver.get(environment.getAuthorUrl() + "/sites.html" + rootPage);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'foundation-collection-container')]")));
		SiteAdminPage adminPage = FactoryProducer.getPageFactory().getSiteAdminPage(driver, wait, environment.getVersion());
		List<WebElement> pages = adminPage.getElements(By.xpath("//article[@itemprop='item']"));
		List<String> pageUris = new ArrayList<String>();
		for (WebElement page : pages) {
			String uri = page.getAttribute("data-path");
			pageUris.add(uri);
		}
		
		driver.get(environment.getAuthorUrl() + AEM_6_EDITOR + TEST_PAGE);
		authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.switchToContent();
		List<WebElement> navigationLinks = authorPage.getElements(By
				.xpath("//ul[@class='nav nav-pills']/li[@role='presentation']/a"));

		for (WebElement link : navigationLinks) {
			String linkHref = link.getAttribute("href");
			boolean foundLink = false;
			for (String uri : pageUris) {
				if (uri != null) {
					if (linkHref != null & linkHref.endsWith(uri + ".html")) {
						foundLink = true;
						break;
					}
				}
			}
			Assert.assertTrue(linkHref + " link not found", foundLink);
		}
	}

	@Test
	public void testPublishValues() throws Exception {
		testFooterLinks();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.assertExists(FOOTER_BY);
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
