package com.cqblueprints.testing.cq.tests.siteadmin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cqblueprints.testing.cq.base.DefaultSiteAdminBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.LoginPage;
import com.cqblueprints.testing.cq.pageobjects.SiteAdminPage;

public class PagePropertyTest extends DefaultSiteAdminBase {
	public static final String PAGE_PROPERTIES_URL = "/libs/wcm/core/content/sites/properties.html";
	public WebDriver pagePropDriver;
	public static final String newTitle = "newtitle";
	
	@Before
	public void setupPagePropDriver() throws InterruptedException {
		pagePropDriver = new FirefoxDriver();
		pagePropDriver.get(environment.getAuthorUrl());
		LoginPage loginPage = FactoryProducer.getPageFactory().getLoginPage(driver, wait, environment.getVersion());
		loginPage.loginAs(environment.getTestUser(), environment.getTestPassword());
		pagePropDriver.get(environment.getAuthorUrl()+PAGE_PROPERTIES_URL+TEST_PAGE.replace(".html", ""));
		pagePropDriver.manage().window().maximize();
	}
	
	@Test
	public void testPagePropTitle() throws InterruptedException {
		String newTitle = "newtitle";
		String titlePropName = "./jcr:title";
		siteAdminPage.validatePageTitle(TEST_PAGE_NAME);
		SiteAdminPage pagePropPage = FactoryProducer.getPageFactory().getSiteAdminPage(driver, wait, environment.getVersion());
		pagePropPage.clickSiteAdminButton("Edit");
		pagePropPage.fillInDialogFieldByName(titlePropName, newTitle);
		pagePropPage.clickSiteAdminButton("Done");
		Thread.sleep(3000); // UI Lags
		pagePropPage.validatePagePropTitle(TEST_PAGE_NAME+newTitle);		
	}
	
	@Test
	public void testPagePropPageTitle() throws InterruptedException {
		
		String titlePropName = "./pageTitle";
		siteAdminPage.validatePageTitle(TEST_PAGE_NAME);
		SiteAdminPage pagePropPage = FactoryProducer.getPageFactory().getSiteAdminPage(driver, wait, environment.getVersion());
		pagePropPage.clickSiteAdminButton("Edit");
		pagePropPage.fillInDialogFieldByName(titlePropName, newTitle);
		pagePropPage.clickSiteAdminButton("Done");
		driver.get(environment.getAuthorUrl()+AEM_6_EDITOR+TEST_PAGE);
		driver.manage().window().maximize();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.validatePageTitle(newTitle);
	}
	
	@Test
	public void testMetaDataSubtitle() throws InterruptedException {
		String newText = "newsubtitle";
		String propName = "./subtitle";
		SiteAdminPage pagePropPage = FactoryProducer.getPageFactory().getSiteAdminPage(driver, wait, environment.getVersion());
		pagePropPage.clickSiteAdminButton("Edit");
		pagePropPage.fillInDialogFieldByName(propName, newText);
		pagePropPage.clickSiteAdminButton("Done");
		Thread.sleep(3000); // UI Lag
		driver.get(environment.getAuthorUrl()+TEST_PAGE);
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.validateMetaData("subtitle", newText);
	}
	
	@Test
	public void testMetaDataDescription() throws InterruptedException {
		String newDescription = "new description";
		String descriptionPropName = "./jcr:description";
		SiteAdminPage pagePropPage = FactoryProducer.getPageFactory().getSiteAdminPage(driver, wait, environment.getVersion());
		pagePropPage.clickSiteAdminButton("Edit");
		pagePropPage.fillInDialogFieldByName(descriptionPropName, newDescription);
		pagePropPage.clickSiteAdminButton("Done");
		Thread.sleep(3000); // UI Lag
		driver.get(environment.getAuthorUrl()+TEST_PAGE);
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.validateMetaData("description", newDescription);
	}
	
	
	@After
	public void closePagePropDriver() {
		pagePropDriver.close();
	}
	
}
