package com.cqblueprints.testing.cq.tests.siteadmin;

import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultSiteAdminBase;

public class WCMSettingsTest extends DefaultSiteAdminBase {
	
	@Test
	public void userSettingsTest() {
		siteAdminPage.clickSiteAdminLink("User Settings");
		siteAdminPage.clickSiteAdminLink("User Preferences");
		siteAdminPage.validateUserSettings();
	}
	
	@Test
	public void windowManagementTest() {
		siteAdminPage.clickSiteAdminLink("User Settings");
		siteAdminPage.clickSiteAdminLink("User Preferences");
		// TODO: Create user programatically and test window selection
	}
	
	@Test
	public void testSiteAdminLinks() {
		siteAdminPage.validateSidepanelLinks(); 
	}
	
	@Test
	public void impersonateUser() {
		
	}
	
}
