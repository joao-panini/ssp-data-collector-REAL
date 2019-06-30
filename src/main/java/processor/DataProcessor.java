package processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import entity.WomanViolenceIndicatorsEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import services.WomanViolenceIndicatorsServiceImpl;
import utils.WorksheetUtils;

public class DataProcessor {

    @Autowired
    private static WomanViolenceIndicatorsServiceImpl womanViolenceIndicatorsService;

    
    private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);

    // TODO handle other sheet format, at this moment just one is handled.
    public static void  consolidateDataAndSave(List<String> filesName) {
        for (String fileName : filesName) {
            try {
                FileInputStream file = new FileInputStream(new File(fileName));
                Workbook workBook;
                if (fileName.endsWith(".xls")) {
                    workBook = new HSSFWorkbook(file);
                } else if (fileName.endsWith(".xlsx")) {
                    workBook = new XSSFWorkbook(file);

                    Sheet datatypeSheet = workBook.getSheetAt(0);
                    // TODO store the @dataMatrix values into database. Call the repository.
                    Vector<Vector<String>> dataMatrix = WorksheetUtils.getContentFromSheet(datatypeSheet);

                    for (int i=1; i <= dataMatrix.size();i++){
                        WomanViolenceIndicatorsEntity WVIEntity = new WomanViolenceIndicatorsEntity();
                        for (int j=0;i <= dataMatrix.get(i).size();j++){
                            WVIEntity.setAno(dataMatrix.get(i).get(j));
                            WVIEntity.setMes(dataMatrix.get(i).get(j));
                            WVIEntity.setNumAmeacas(Integer.parseInt(dataMatrix.get(i).get(j)));
                            WVIEntity.setNumLesoesCorporais(Integer.parseInt(dataMatrix.get(i).get(j)));
                            WVIEntity.setNumEstupros(Integer.parseInt(dataMatrix.get(i).get(j)));
                            WVIEntity.setNumFeminicidioConsumado(Integer.parseInt(dataMatrix.get(i).get(j)));
                            WVIEntity.setNumFeminicidioTentado(Integer.parseInt(dataMatrix.get(i).get(j)));
                            womanViolenceIndicatorsService.saveIndicator(WVIEntity);
                        }
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
