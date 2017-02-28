package com.cqblueprints.testing.cq.tests.components.general;

import org.junit.Test;
import com.cqblueprints.testing.cq.base.DefaultComponentBase;
import com.cqblueprints.testing.cq.pageobjects.AuthorPage;
import com.cqblueprints.testing.cq.factory.FactoryProducer;

public class GeneralCarouselTest extends RetailBaseTest {
	public static final String COMPONENT_NAME = "Carousel";
	public static final String TARGET_PARSYS_NAME = "par";
	public static final String COMPONENT_CRX_NAME = "carousel";

	public static final String COMPONENT_TAB_ONE_NAME = "Carousel";
	public static final String COMPONENT_TAB_TWO_NAME = "List";

	public static final String BUILD_LIST_OPTION_XPATH = "//span[@data-cq-dialog-dropdown-showhide-target='.carousel-option-listfrom-showhide-target']/button";
	public static final String BUILD_LIST_STATIC_OPTION_XPATH = "//li[@data-value='static']";
	public static final String ADD_PAGE_XPATH = "//div[@data-init='multifield']/button/i[contains(@class,'coral-Icon--addCircle')]";
	public static final String ADD_PAGE_NAME = "./pages";
	public static final String EXAMPLE_CAROUSEL_PAGE = "/content/geometrixx/en/events/techsummit";

	@Test
	public void placeComponent() throws Exception {
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.toggleSidePanel();
		authorPage.selectSidePanelTab(COMPONENTS_TAB_NAME);
		authorPage.dragComponentIntoParsys(COMPONENT_NAME, TARGET_PARSYS_NAME);
	}

	@Test
	public void testCarousel() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.editComponent(COMPONENT_CRX_NAME);
		authorPage.selectDialogTab(COMPONENT_TAB_TWO_NAME);
		authorPage.clickByXpath(BUILD_LIST_OPTION_XPATH);
		authorPage.clickByXpath(BUILD_LIST_STATIC_OPTION_XPATH);
		authorPage.clickByXpath(ADD_PAGE_XPATH);
		authorPage.fillInDialogFieldByName(ADD_PAGE_NAME, EXAMPLE_CAROUSEL_PAGE);
		authorPage.confirmDialog();
	}

	@Test
	public void deleteComponent() throws Exception {
		placeComponent();
		AuthorPage authorPage = FactoryProducer.getPageFactory().getAuthorPage(driver, wait, environment.getVersion());
		authorPage.deleteComponent(COMPONENT_CRX_NAME);
	}

}
