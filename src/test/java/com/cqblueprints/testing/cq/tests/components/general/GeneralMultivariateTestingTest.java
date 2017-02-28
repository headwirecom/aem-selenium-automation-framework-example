package com.cqblueprints.testing.cq.tests.components.general;


import junit.framework.Assert;

import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralMultivariateTestingTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "mvt";
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Multivariate Testing", "par");
	}
	
	@Test
	public void testMultivariateTesting() throws Exception {
			Assert.fail("Not implemented yet");
			placeComponent();
			AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			authorPage.editComponent("mvt");
	}
	
	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
