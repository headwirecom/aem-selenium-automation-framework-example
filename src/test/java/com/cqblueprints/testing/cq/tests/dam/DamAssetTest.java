package com.cqblueprints.testing.cq.tests.dam;

import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultDamAdminBase;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class DamAssetTest extends DefaultDamAdminBase {
	private static final String TEST_ASSET_PATH = "/content/dam/geometrixx/portraits";
	private static final String TEST_ASSET_NAME = "scott_reynolds.jpg";

	@Test
	public void testAssetTimeline() {
		driver.get(environment.getAuthorUrl()+AEM_6_DAM+TEST_ASSET_PATH);
		damPage = FactoryProducer.getPageFactory().getDAMPage(driver, wait, environment.getVersion());
		damPage = damPage.viewTimeline(TEST_ASSET_NAME);
		damPage = damPage.verifyTimelineComment("There are currently no entries.");
	}
	
	@Test
	public void testAssetReferences() {
		String[] siteReferences = { "Board of Directors", "Scott Recommends" };
		driver.get(environment.getAuthorUrl()+AEM_6_DAM+TEST_ASSET_PATH);
		damPage = FactoryProducer.getPageFactory().getDAMPage(driver, wait, environment.getVersion());
		damPage = damPage.viewReferences(TEST_ASSET_NAME);
		damPage = damPage.openSiteReferences();
		damPage = damPage.verifyReferences(siteReferences);
	}
	
}
