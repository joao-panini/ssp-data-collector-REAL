package com.collector;

import java.util.List;

public interface CrawlingService {
    List<String> getFileLinks(String urlToCrawl);
}
