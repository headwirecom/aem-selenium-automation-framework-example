package com.cqblueprints.testing.cq.tests.components.personalization;


import junit.framework.Assert;

import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class PersonalizationTeaserTest extends DefaultComponentBase {
	@Test
	public void testTeaser() throws Exception {
			Assert.fail("Not implemented yet.");
			AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			authorPage.toggleSidePanel();
			authorPage.selectSidePanelTab("Components");
			authorPage.dragComponentIntoParsys("Teaser", "par");
			authorPage.checkContentByClass("teaser");
			authorPage.editComponent("teaser");
	}
}
