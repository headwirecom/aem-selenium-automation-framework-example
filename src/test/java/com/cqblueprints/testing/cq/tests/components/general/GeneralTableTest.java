package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralTableTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "table";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Table", "par");
	}

	@Test
	public void testTable() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToOldDialogIframe();
		authorPage.switchToRTEIframe();
		authorPage.typeInRTETable("Test Text", 1, 3);
		authorPage.typeInRTETable("Test Text", 2, 2);
		authorPage.confirmDialogOld();
	}
	
	@Test
	public void deleteComponent() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
