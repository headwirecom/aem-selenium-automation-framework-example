package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;
import org.openqa.selenium.By;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class GeneralChartTest extends DefaultComponentBase {

	public static final String COMPONENT_CRX_NAME = "chart";
	public static final String CHART_DATA_FIELD_NAME = "./chartData";
	public static final String CHART_DATA = "Q1,Q2,Q3,Q4\nManifolds,10,20,30,40\nTensors,5,7,8,10\nQuantum Fluctuations,20,30,50,40";

	public static final String CHART_TYPE_FIELD_NAME = "./chartType";
	public static final String CHART_TYPE_XPATH = "//div[@class='x-combo-list-item' and text()='Bar Chart']";

	public static final String tableXpath = "//div[contains(@class,'chart')]/table";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Chart", "par");
	}

	@Test
	public void testChart() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToOldDialogIframe();
		authorPage.fillInDialogFieldByName(CHART_DATA_FIELD_NAME, CHART_DATA);
		authorPage.selectDialogTabX("Advanced");
		authorPage.selectDropDownValue("Bar Chart");
		authorPage.confirmDialogOld();
		authorPage.switchToDefaultContent();
		authorPage.switchToContent();
		authorPage.assertExists(By.xpath(tableXpath));
		
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}

	@Test
	public void testPublishValues() throws Exception {
		testChart();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.activatePage(TEST_PAGE, environment);
		PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
		try {
			publishPage.assertExists(By.xpath(tableXpath));
		} finally {
			authorPage.deactivatePage(TEST_PAGE, environment);
			publishPage.closeDriver();
		}
	}
}
