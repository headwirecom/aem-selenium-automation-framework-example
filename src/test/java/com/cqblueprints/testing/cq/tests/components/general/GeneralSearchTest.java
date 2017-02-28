package com.cqblueprints.testing.cq.tests.components.general;


import org.junit.Test;

import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralSearchTest extends DefaultComponentBase {
	public static final String COMPONENT_CRX_NAME = "search";
	public static final String SEARCH_IN_PATH = "/content/geometrixx/en";
	public static final String SEARCH_BUTTON_TEXT = "startsearch";
	public static final String STATISTICS_TEXT = "found this many pages";
	public static final String NO_RESULTS_TEXT = "there were no results";
	public static final String SPELLCHECK_TEXT = "incorrect spelling";
	public static final String SIMILAR_PAGES_TEXT = "pages you might also like";
	public static final String RELATED_SEARCHES_TEXT = "similar searches performed";
	public static final String SEARCH_TRENDS_TEXT = "these searches are trending";
	public static final String RESULT_PAGES_TEXT = "results page label";
	public static final String PREVIOUS_LABEL_TEXT = "previous page label";
	public static final String NEXT_LABEL_TEXT = "next page label";
	
	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab("Components");
		authorPage.dragComponentIntoParsys("Search", "par");
	}

	@Test
	public void testSearch() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.switchToOldDialogIframe();
		authorPage.fillInDialogFieldByName("./searchIn", SEARCH_IN_PATH);
		authorPage.fillInDialogFieldByName("./searchButtonText", SEARCH_BUTTON_TEXT);
		authorPage.fillInDialogFieldByName("./statisticsText", STATISTICS_TEXT);
		authorPage.fillInDialogFieldByName("./noResultsText", NO_RESULTS_TEXT);
		authorPage.fillInDialogFieldByName("./spellcheckText", SPELLCHECK_TEXT);
		authorPage.fillInDialogFieldByName("./similarPagesText", SIMILAR_PAGES_TEXT);
		authorPage.fillInDialogFieldByName("./relatedSearchesText", RELATED_SEARCHES_TEXT);
		authorPage.fillInDialogFieldByName("./searchTrendsText", SEARCH_TRENDS_TEXT);
		authorPage.fillInDialogFieldByName("./resultPagesText", RESULT_PAGES_TEXT);
		authorPage.fillInDialogFieldByName("./previousText", PREVIOUS_LABEL_TEXT);
		authorPage.fillInDialogFieldByName("./nextText", NEXT_LABEL_TEXT);	
	}
	
	@Test
	public void testPublishValues() throws Exception {
		testSearch();
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();	
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}
}
