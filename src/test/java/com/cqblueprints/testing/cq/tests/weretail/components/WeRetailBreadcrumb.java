package com.cqblueprints.testing.cq.tests.weretail.components;


import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;
import com.cqblueprints.testing.cq.tests.components.general.RetailBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.HashMap;

public class WeRetailBreadcrumb extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Breadcrumb";
	public static final String COMPONENT_CRX_NAME = "breadcrumb";

	public static final String NAVIGATION_LEVEL_LABEL = "Navigation-Level to";
	public static final String HIDE_CURRENT_LABEL = "Hide Current";
	public static final String SHOW_HIDDEN_LABEL = "Show Hidden";

	HashMap<String, String> fields = new HashMap<String, String>(){{
		put(NAVIGATION_LEVEL_LABEL, "./startLevel");
		put(HIDE_CURRENT_LABEL, "./hideCurrent");
		put(SHOW_HIDDEN_LABEL, "./showHidden");
	}};

	@Test
	public void testBreadcrumbLevel() throws Exception {
		String[] validLinks = {"Canada", "English", "testparent"};

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get(NAVIGATION_LEVEL_LABEL), "2");
		authorPage.confirmDialog();
		authorPage.switchToContent();

		for (String link : validLinks) {
			authorPage.assertExists(By.xpath("//li[@class='breadcrumb-item']/a[text()='"+link+"']"));
		}
		authorPage.assertNotExists(By.xpath("//li[@class='breadcrumb-item']/a[text()='"+TEST_PAGE_NAME+"']"));
	}

	@Test
	public void testHideCurrent() throws Exception {
		String[] validLinks = {"Canada", "English", "testparent"};

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get(NAVIGATION_LEVEL_LABEL), "2");
		authorPage.clickBy(By.name(fields.get(HIDE_CURRENT_LABEL)));
		authorPage.confirmDialog();
		authorPage.switchToContent();

		for (String link : validLinks) {
			authorPage.assertExists(By.xpath("//li[@class='breadcrumb-item']/a[text()='"+link+"']"));
		}
		authorPage.assertExists(By.xpath("//li[@class='breadcrumb-item--active' and text()='"+TEST_PAGE_NAME+"']"));
	}

	@Test
	public void testPublishValues() throws Exception {
		String[] validLinks = {"Canada", "English", "testparent"};

		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.fillInDialogFieldByName(fields.get(NAVIGATION_LEVEL_LABEL), "2");
		authorPage.confirmDialog();
		authorPage.switchToContent();
		authorPage.activatePage(TEST_PAGE, environment);

		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			for (String link : validLinks) {
				publishPage.assertExists(By.xpath("//li[@class='breadcrumb-item']/a[text()='"+link+"']"));
			}
			publishPage.assertNotExists(By.xpath("//li[@class='breadcrumb-item']/a[text()='"+TEST_PAGE_NAME+"']"));
		} finally {
			publishPage.closeDriver();
		}
		authorPage.deactivatePage(TEST_PAGE, environment);
	}
}
