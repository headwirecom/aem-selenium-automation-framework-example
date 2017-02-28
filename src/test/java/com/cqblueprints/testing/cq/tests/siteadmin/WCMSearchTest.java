package com.cqblueprints.testing.cq.tests.siteadmin;

import static com.cqblueprints.testing.cq.base.BaseActions.ACTIONS;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultSiteAdminBase;

public class WCMSearchTest extends DefaultSiteAdminBase {
	public static final String TEST_PAGE_PARENT = "/content/geometrixx/en/";
	public static final String TEST_PAGE_NAME = "uniquetestpage";
	public static final String TEST_PAGE_TEMPLATE = "/apps/geometrixx/templates/contentpage";
	public static final String TEST_PAGE = TEST_PAGE_PARENT+TEST_PAGE_NAME+".html";
	
	@Test
	public void searchResultsTest() throws ClientProtocolException, IOException {
		ACTIONS.createPage(TEST_PAGE_NAME, TEST_PAGE_PARENT, TEST_PAGE_TEMPLATE, environment);
		try {
			siteAdminPage.search(TEST_PAGE_NAME);
			siteAdminPage.validateNumberOfSearchResults(1);
		} finally {
			ACTIONS.deletePage(TEST_PAGE.replace(".html", ""), environment);
		}
	}
	
}
