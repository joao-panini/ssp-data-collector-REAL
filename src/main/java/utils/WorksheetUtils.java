package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


import java.util.Vector;

public class WorksheetUtils {
    private static DataFormatter formatter = new DataFormatter();
    private static final String START_COLLECTING_TARGET = "ANO";








    private WorksheetUtils() {

    }

    public static Vector<Vector<String>> getContentFromSheet(Sheet sheet) {
        Vector<Vector<String>> contentMatrix = new Vector<>();
        boolean startCollecting = false;

        for (Row sheetRow : sheet) {
            Vector<String> contentRow = new Vector<>();
            for (Cell cell : sheetRow) {
                String cellValue = formatter.formatCellValue(cell).trim();
                if (!startCollecting && START_COLLECTING_TARGET.equals(cellValue))
                    startCollecting = true;

                if (startCollecting && !"".equals(cellValue))
                    contentRow.add(cellValue.replace("*", "").trim());
            }
            if (contentRow.size() > 1)
                contentMatrix.add(contentRow);
        }
        return contentMatrix;
    }
}
