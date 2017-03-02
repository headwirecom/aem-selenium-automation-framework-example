package com.cqblueprints.testing.cq.tests.siteadmin;

import com.cqblueprints.testing.cq.base.DefaultSiteAdminBase;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import java.io.IOException;

import static com.cqblueprints.testing.cq.base.BaseActions.ACTIONS;


public class PageCreateEditDeleteTest extends DefaultSiteAdminBase {
	public static final String pageXpath = "//article[@data-item-title='"+TEST_PAGE_NAME+"']";
	public static final String newPageName = "newpage1";
	public static final String selectOptionTitle = "Enter Selection";
	public static final String selectOptionTitleFallback = "Select";
	
	@Test
	public void createPageTest() throws ClientProtocolException, IOException {	
		String templateThumbnail = "/apps/geometrixx/templates/contentpage/thumbnail.png";	
		//driver.get(environment.getAuthorUrl()+"/sites.html"+TEST_PAGE_PARENT+TEST_PAGE_NAME);
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.clickSiteAdminLink("Create");
		siteAdminPage.selectDropdownItem("Create Page");
		siteAdminPage.createPageWithTemplate(templateThumbnail, newPageName);
		ACTIONS.deletePage(TEST_PAGE_PARENT+"/"+newPageName, environment);
	}
	
	@Test
	public void movePageTest() throws InterruptedException {
		siteAdminPage.clickSiteAdminButton(selectOptionTitle, selectOptionTitleFallback);
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.clickSiteAdminButton("Move");
		Thread.sleep(500);
		siteAdminPage.renamePage(newPageName+"-renamed");
		siteAdminPage.clickButtonByText("Next");
	}
	
	@Test
	public void activatePage() throws InterruptedException {
		siteAdminPage.clickSiteAdminButton(selectOptionTitle, selectOptionTitleFallback);
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.activatePage();
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.isNot404(TEST_PAGE_NAME);
		publishPage.closeDriver();
	}
	
	@Test
	public void deactivatePage() throws InterruptedException {
		siteAdminPage.clickSiteAdminButton(selectOptionTitle, selectOptionTitleFallback);
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.activatePage();
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.deactivatePage();
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver,environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		publishPage.is404(newPageName);
		publishPage.closeDriver();
	}
	
	@Test
	public void deletePageTest() throws InterruptedException {
		siteAdminPage.clickSiteAdminButton(selectOptionTitle, selectOptionTitleFallback);
		siteAdminPage.clickSiteAdminPage(pageXpath);
		siteAdminPage.clickSiteAdminLink("Delete");
		siteAdminPage.clickButtonByText("Delete");
		Thread.sleep(2000);
		siteAdminPage.validatePageDeleted(pageXpath);
	}
	
	
	
}
