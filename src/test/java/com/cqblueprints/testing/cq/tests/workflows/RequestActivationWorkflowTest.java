package com.cqblueprints.testing.cq.tests.workflows;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cqblueprints.testing.cq.base.DefaultWorkflowBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;
import com.cqblueprints.testing.cq.pageobjects.InboxPage;
import com.cqblueprints.testing.cq.pageobjects.PublishPage;

public class RequestActivationWorkflowTest extends DefaultWorkflowBase {
	private final String WORKFLOW_COMMENT = "Test comment";	
	private final String AEM_5_EDITOR = "/cf#";
	private final String REQUEST_ACTIVATION_WORKFLOW = "Request for Activation";
	
		@Test
		public void requestActivationWorkflowThroughSidekick() throws InterruptedException {
			driver.get(environment.getAuthorUrl()+AEM_5_EDITOR+TEST_PAGE);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentFrame")));
			AuthorPage dscPageNotStarted = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			AuthorPage dscPageSecondStep = dscPageNotStarted.startWorkflowFromSidekick(REQUEST_ACTIVATION_WORKFLOW);
			dscPageSecondStep.advanceWorkflowFromSidekick("Ready for activate. Ready for activate.");
			dscPageSecondStep.advanceWorkflowFromSidekick("Approved.");
			PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
			publishPage.isNot404(TEST_PAGE_NAME);
			publishPage.closeDriver();
		}
		
		@Test
		public void requestActivationWorkflowThroughInbox() throws InterruptedException {
			driver.get(environment.getAuthorUrl()+AEM_5_EDITOR+TEST_PAGE);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ContentFrame")));
			AuthorPage dscPageNotStarted = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
			dscPageNotStarted.startWorkflowFromSidekick(REQUEST_ACTIVATION_WORKFLOW);
			Thread.sleep(3000);
			driver.get(environment.getAuthorUrl()+INBOX_URI);
			//Thread.sleep(2000);
			InboxPage inboxPage = FactoryProducer.getPageFactory().getInboxPage(driver, wait, environment.getVersion());
			inboxPage.validateStatus("Approve content");
			inboxPage.advanceWorkflowThroughInbox(TEST_PAGE_NAME, WORKFLOW_COMMENT);
			inboxPage.validateStatus("Waiting for activation");
			inboxPage.validateComment(WORKFLOW_COMMENT);
			inboxPage.advanceWorkflowThroughInbox(TEST_PAGE_NAME, WORKFLOW_COMMENT);
			Thread.sleep(5000); // wait for activation
			PublishPage publishPage = FactoryProducer.getPageFactory().getPublishPage(driver, environment.getPublishUrl()+TEST_PAGE, environment.getVersion());
			publishPage.isNot404(TEST_PAGE_NAME);
			publishPage.closeDriver();
		}
		
}
