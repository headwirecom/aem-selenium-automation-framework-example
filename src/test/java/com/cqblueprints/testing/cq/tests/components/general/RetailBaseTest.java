package com.cqblueprints.testing.cq.tests.components.general;

import com.cqblueprints.testing.cq.base.BaseActions;
import com.cqblueprints.testing.cq.base.TestBase;
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by tp on 1/22/2017.
 */
public class RetailBaseTest extends TestBase {
    protected static final String TEST_PAGE_PARENT = "/content/we-retail/ca/en/testparent";
    protected static final String TEST_PAGE_NAME = "testpage";
    protected static final String TEST_PAGE_TEMPLATE = "/conf/we-retail/settings/wcm/templates/content-page";
    protected static final String TEST_PAGE = TEST_PAGE_PARENT+"/"+TEST_PAGE_NAME+".html";
    protected static final String INBOX_URI = "/inbox";
    protected static final String AEM_6_EDITOR = "/editor.html";
    public static final String COMPONENTS_TAB_NAME = "Components";
    public static final String TARGET_PARSYS = environment.getVersion().equals("6.3") ? "responsivegrid" : "par";

    public RetailBaseTest() {
    }

    @Before
    public void startDriver() throws Exception {
        BaseActions.ACTIONS.createPage(TEST_PAGE_NAME, TEST_PAGE_PARENT, TEST_PAGE_TEMPLATE, environment);
        if(environment.getVersion().startsWith("5.")) {
            driver.get(environment.getAuthorUrl() + TEST_PAGE);
        } else {
            driver.get(environment.getAuthorUrl() + AEM_6_EDITOR + TEST_PAGE);
        }

    }

    @After
    public void closeDriver() throws ClientProtocolException, IOException {
        BaseActions.ACTIONS.deletePage(TEST_PAGE.replace(".html", ""), environment);
        driver.quit();
    }
}
