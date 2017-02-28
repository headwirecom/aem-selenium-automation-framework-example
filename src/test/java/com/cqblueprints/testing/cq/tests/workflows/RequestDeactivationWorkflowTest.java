package com.cqblueprints.testing.cq.tests.workflows;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cqblueprints.testing.cq.base.DefaultWorkflowBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.InboxPage;

public class RequestDeactivationWorkflowTest extends DefaultWorkflowBase {
	private final String WORKFLOW_COMMENT = "Test comment";	
	private final String AEM_5_EDITOR = "/cf#";
	private final String REQUEST_DEACTIVATION_WORKFLOW = "Request for Deactivation";
	
		@Test
		public void requestDeactivationWorkflowThroughSidekick() throws InterruptedException, ClientProtocolException, IOException {
			driver.get(environment.getAuthorUrl()+AEM_5_EDITOR+TEST_PAGE);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentFrame")));
			AuthorPage dscPageNotStarted = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			dscPageNotStarted.activatePage(TEST_PAGE, environment);
			AuthorPage dscPageSecondStep = dscPageNotStarted.startWorkflowFromSidekick(REQUEST_DEACTIVATION_WORKFLOW);
			dscPageSecondStep.advanceWorkflowFromSidekick("Deactivate");
			Thread.sleep(2000);
			dscPageSecondStep.advanceWorkflowFromSidekick("Deactivating");
			Thread.sleep(5000); // wait for deactivation
			driver.get(environment.getPublishUrl()+TEST_PAGE);
			Assert.assertFalse("Page still active", driver.getCurrentUrl().contains(TEST_PAGE));
		}
		
		@Test
		public void requestDectivationWorkflowThroughInbox() throws InterruptedException, ClientProtocolException, IOException {
			driver.get(environment.getAuthorUrl()+AEM_5_EDITOR+TEST_PAGE);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentFrame")));
			AuthorPage dscPageNotStarted = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			dscPageNotStarted.activatePage(TEST_PAGE, environment);
			dscPageNotStarted.startWorkflowFromSidekick(REQUEST_DEACTIVATION_WORKFLOW);
			Thread.sleep(3000);
			driver.get(environment.getAuthorUrl()+INBOX_URI);
			InboxPage inboxPage = FactoryProducer.getPageFactory().getInboxPage(driver, wait, environment.getVersion());
			inboxPage.advanceWorkflowThroughInbox(TEST_PAGE_NAME, WORKFLOW_COMMENT);
			inboxPage.validateComment(WORKFLOW_COMMENT);
			inboxPage.advanceWorkflowThroughInbox(TEST_PAGE_NAME, WORKFLOW_COMMENT);
			Thread.sleep(5000); // wait for deactivation
			driver.get(environment.getPublishUrl()+TEST_PAGE);
			Assert.assertFalse("Page still active", driver.getCurrentUrl().contains(TEST_PAGE));
		}
		
}
