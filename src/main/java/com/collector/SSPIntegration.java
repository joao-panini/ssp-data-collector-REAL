package com.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.processor.DataProcessor;



import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class SSPIntegration {

    @Autowired
    private DataProcessor processor;

    @Autowired
    private DataCollectionService collectionService;

    private static final Logger log = LoggerFactory.getLogger(SSPIntegration.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String SSP_VIOLENCE_AGAINST_WOMAN_LINK = "https://ssp.rs.gov.br/indicadores-da-violencia-contra-a-mulher";
    private static final String CRON = "1 * * * * ?";
    private static final long DEFAULT_RETRY_WAIT = 30000L;

    @Scheduled(cron = CRON)
    public void startDataCollection() throws InterruptedException {
        //log.info("The time now is {}", dateFormat.format(new Date()));

        List<String> filesDownloadLinks = new SeleniumCrawlingServiceImpl().getFileLinks(SSP_VIOLENCE_AGAINST_WOMAN_LINK);
        if (filesDownloadLinks.size() == 0) {
            log.warn("No links found, waiting for 30 seconds and trying one more time...");
            Thread.sleep(DEFAULT_RETRY_WAIT);
            filesDownloadLinks = new SeleniumCrawlingServiceImpl().getFileLinks(SSP_VIOLENCE_AGAINST_WOMAN_LINK);
        }

        List<String> fileNames = collectionService.getFilesFromLinks(filesDownloadLinks);
        if (fileNames.size() == 0) {
            log.warn("Not able to download the files, waiting for 30 seconds and trying one more time...");
            Thread.sleep(DEFAULT_RETRY_WAIT);
            fileNames = collectionService.getFilesFromLinks(filesDownloadLinks);
        }

        processor.consolidateDataAndSave(fileNames);
    }
}
