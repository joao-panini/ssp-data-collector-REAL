package com.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.entity.WVIEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.services.WVIService;
import com.utils.WorksheetUtils;


@Component
public class DataProcessor {

    @Autowired
    private WVIService wviService;


    private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);

    public void  consolidateDataAndSave(List<String> filesName) {
        for (String fileName : filesName) {
            try {
                FileInputStream file = new FileInputStream(new File(fileName));
                Workbook workBook;
                if (fileName.endsWith(".xls")) {
                    workBook = new HSSFWorkbook(file);
                } else if (fileName.endsWith(".xlsx")) {
                    workBook = new XSSFWorkbook(file);

                    Sheet datatypeSheet = workBook.getSheetAt(0);
                    Vector<Vector<String>> dataMatrix = WorksheetUtils.getContentFromSheet(datatypeSheet);

                    for (int i=1; i <= dataMatrix.size();i++){
                        WVIEntity wviEntity = new WVIEntity();
                        wviEntity.setAno(dataMatrix.get(i).get(0));
                        wviEntity.setMes(dataMatrix.get(i).get(1));
                        wviEntity.setNumAmeacas(dataMatrix.get(i).get(2));
                        wviEntity.setNumLesoesCorporais(dataMatrix.get(i).get(3));
                        wviEntity.setNumEstupros(dataMatrix.get(i).get(4));
                        wviEntity.setNumFeminicidioConsumado(dataMatrix.get(i).get(5));
                        wviEntity.setNumFeminicidioTentado(dataMatrix.get(i).get(6));
                        wviService.saveIndicator(wviEntity);
                    }

                } else {
                    log.error("Unexpected sheet format: {}", fileName.substring(fileName.lastIndexOf(".")));
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
