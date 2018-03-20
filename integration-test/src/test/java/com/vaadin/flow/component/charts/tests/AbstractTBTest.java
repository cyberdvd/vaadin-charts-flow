/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */
package com.vaadin.flow.component.charts.tests;

import com.saucelabs.ci.sauceconnect.AbstractSauceTunnelManager;
import com.vaadin.flow.component.charts.AbstractChartExample;
import com.vaadin.flow.component.charts.testbench.ChartElement;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.BrowserUtil;
import com.vaadin.testbench.parallel.DefaultBrowserFactory;
import com.vaadin.testbench.parallel.ParallelTest;
import com.vaadin.testbench.parallel.TestBenchBrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunOnHub
public abstract class AbstractTBTest extends ParallelTest {

    private static final String PROPERTY_TEST_ALL_BROWSERS = "test.allBrowsers";
    private static final String PROPERTY_SAUCE_OPTIONS = "sauce.options";
    private static final String PROPERTY_SAUCE_SAUCE_ACCESS_KEY = "sauce.sauceAccessKey";
    private static final String PROPERTY_SAUCE_USER = "sauce.user";

    @Override
    public void setup() throws Exception {
        super.setup();
        openTestURL();
    }

    protected ChartElement getChartElement() {
        return $(ChartElement.class).waitForFirst();
    }

    protected WebElement getElementFromShadowRoot(WebElement shadowRootOwner,
                                                  By by) {
        return getElementFromShadowRoot(shadowRootOwner, by, 0);
    }

    protected WebElement getElementFromShadowRoot(WebElement shadowRootOwner,
            By by, int index) {
        WebElement shadowRoot = (WebElement) executeScript(
                "return arguments[0].shadowRoot", shadowRootOwner);
        assertNotNull("Could not locate shadowRoot in the element", shadowRoot);


        List<WebElement> elements = shadowRoot.findElements(by);
        if (elements.size() > index) {
            return elements.get(index);
        }

        throw new AssertionError(
                "Could not find required element in the shadowRoot");
    }

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowserConfiguration() {
        if (System.getProperty(PROPERTY_TEST_ALL_BROWSERS) == null) {
            return Arrays.asList(BrowserUtil.chrome());
        }

        TestBenchBrowserFactory browserFactory = new DefaultBrowserFactory();
        return Arrays.asList(
                BrowserUtil.chrome(),
                BrowserUtil.firefox(),
                DesiredCapabilities.iphone(),
                DesiredCapabilities.ipad(),
                browserFactory.create(Browser.SAFARI, "11.0", Platform.SIERRA),
                browserFactory.create(Browser.EDGE, "16", Platform.WIN10),
                browserFactory.create(Browser.IE11, "11", Platform.WIN10),
                browserFactory.create(Browser.IE11, "11", Platform.WINDOWS));
    }

    @Override
    public void setDesiredCapabilities(
            DesiredCapabilities desiredCapabilities) {
        String tunnelId = AbstractSauceTunnelManager
                .getTunnelIdentifier(System.getProperty(PROPERTY_SAUCE_OPTIONS), null);
        if (tunnelId != null) {
            desiredCapabilities.setCapability("tunnelIdentifier", tunnelId);
        }
        super.setDesiredCapabilities(desiredCapabilities);
    }

    @Override
    protected String getHubURL() {
        String username = System.getProperty(PROPERTY_SAUCE_USER);
        String accessKey = System.getProperty(PROPERTY_SAUCE_SAUCE_ACCESS_KEY);

        if (username == null) {
            throw new IllegalArgumentException(
                    "You must give a Sauce Labs user name using -Dsauce.user=<username> "
                            + "or by adding sauce.user=<username> to local.properties");
        }
        if (accessKey == null) {
            throw new IllegalArgumentException(
                    "You must give a Sauce Labs access key using -Dsauce.sauceAccessKey=<accesskey> "
                            + "or by adding sauce.sauceAccessKey=<accesskey> to local.properties");
        }
        return "http://" + username + ":" + accessKey
                + "@localhost:4445/wd/hub";
    }

    protected void openTestURL() {
        String url = getTestUrl();
        driver.get(url);
    }

    /**
     * Returns the full URL to be used for the test
     *
     * @return the full URL for the test
     */
    protected String getTestUrl() {
        String baseUrl = getBaseURL();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        return baseUrl + getDeploymentPath();
    }

    /**
     * Returns the path that should be used for the test. The path contains the
     * full path (appended to hostname+port) and must start with a slash.
     *
     * @return The URL path to the UI class to test
     */
    protected String getDeploymentPath() {
        return "/" + getTestView().getCanonicalName();
    }

    /**
     * Used to determine what URL to initially open for the test
     *
     * @return The base URL for the test. Does not include a trailing slash.
     */
    protected String getBaseURL() {
        return "http://" + getDeploymentHostname() + ":" + getDeploymentPort();
    }

    protected String getDeploymentHostname() {
        return "localhost";
    }

    protected int getDeploymentPort() {
        return 8080;
    }

    protected abstract Class<? extends AbstractChartExample> getTestView();

}
