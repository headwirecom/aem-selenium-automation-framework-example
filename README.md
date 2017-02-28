# Installation

### Maven

To use the framework in a maven project, add the following dependency:

<dependency>
	<groupId>com.cqblueprints.testing</groupId>
	<artifactId>test-framework</artifactId>
	<version>0.4.0</version>
</dependency>>

### Jar File

The jar file can be downloaded from: http://dev.headwire.com/nexus/service/local/repositories/releases/content/com/headwire/testing/test-framework/0.0.1/test-framework-0.0.1-tests.jar


### Usage

To begin using the built in functionality you must create a class that extends the TestBase class:

public class ComponentTest extends TestBase {}

The TestBase class instantiates the WebDriver and sets up the environment from a json file. To write test cases, the page objects provide high level AEM actions that remove the need to use selenium actions directly in tests. Example:

@Test
public void openSiteAdmin() {
LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
WelcomePage welcomePage = loginPage.loginAs(environment.getTestUser(), environment.getTestPassword());		
SiteAdminPage siteAdminPage = welcomePage.openSiteAdmin();
}

More examples can be found here: http://git.headwire.com/aem-archetypes/aem6-simple-archetype/tree/master/src/main/archetype/ui.tests 

