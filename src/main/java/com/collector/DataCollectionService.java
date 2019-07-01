package com.collector;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class DataCollectionService {

    private static final Logger log = LoggerFactory.getLogger(DataCollectionService.class);

    private static final int CONNECTION_TIMEOUT = 2500;
    private static final int READ_TIMEOUT = 2500;

    public List<String> getFilesFromLinks(Collection<String> fileLinks) {
        fileLinks = new HashSet<>(fileLinks);
        List<String> filesName = new ArrayList<>();
        log.info("Repeated links were removed from download queue, total of files: {}", fileLinks.size());
        for (String link : fileLinks) {
            String fileName = link.substring(link.lastIndexOf("/") + 1);
            log.info("Downloading: {}", fileName);
            try {
                FileUtils.copyURLToFile(
                        new URL(link),
                        new File(fileName),
                        CONNECTION_TIMEOUT,
                        READ_TIMEOUT);
                filesName.add(fileName);
            } catch (IOException e) {
                log.error("Not able to download the file: {}", fileName);
            }
        }
        return filesName;
    }
}
