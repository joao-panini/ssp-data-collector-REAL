package collector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumCrawlingServiceImpl implements CrawlingService{

    private static final Logger log = LoggerFactory.getLogger(SeleniumCrawlingServiceImpl.class);
    private static final String DOWNLOAD_LINK_XPATH = "//p//a[contains(@href, '/upload/')][1]";
    private ChromeDriverService chromeDriverService;

    private void startChromeService() {
        try {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("chromedriver.exe"))
                    .build();
        } catch (Exception e) {
            log.error("Failed to create chrome driver service with the following message: {}", e.getMessage());
        }
    }

    private void stopChromeService() {
        if (chromeDriverService != null && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }

    private WebDriver getHeadlessChrome() {
        startChromeService();
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--headless");
        return new ChromeDriver(chromeDriverService, chromeOptions);
    }

    @Override
    public List<String> getFileLinks(String urlToCrawl) {
        WebDriver webDriver = getHeadlessChrome();
        webDriver.get(urlToCrawl);
        List<String> links = webDriver.findElements(By.xpath(DOWNLOAD_LINK_XPATH))
                .stream()
                .map(x -> x.getAttribute("href"))
                .collect(Collectors.toList());
        webDriver.quit();
        stopChromeService();
        log.info("Total of links found : {}", links.size());
        return links;
    }
}
