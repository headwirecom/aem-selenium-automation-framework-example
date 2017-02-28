package com.cqblueprints.testing.cq.tests.components.bootstrap;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultBootstrapComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class BootstrapColumnsTest extends DefaultBootstrapComponentBase {
	public static final String COMPONENT_CRX_NAME = "columns";
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Columns", "mainParsys");
	}
	
	@Test
	public void testBootstrapTwoColumns() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(2);
		authorPage = authorPage.fillInMultipleFields("./columns", "10");
		authorPage.confirmDialog();
		authorPage.assertExists(By.xpath("//div[@data-text='Start of 2 Columns']"));
	}
	@Test
	public void testBootstrapThreeColumns() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage = authorPage.addTabs(3);
		authorPage.confirmDialog();
		authorPage.assertExists(By.xpath("//div[@data-text='Start of 2 Columns']"));
	}
	
	@Test
	public void deleteComponent() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
