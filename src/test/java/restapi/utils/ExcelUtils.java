package restapi.utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public int rowCount;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public ExcelUtils(){
        try{
        //String excelPath = "./Data/testingData.xlsx";
        workbook = new XSSFWorkbook("D://WebDevelopment//apitestpractice//Data//testingData.xlsx");
        sheet = workbook.getSheet("APIDATA");

        
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
    }

    public String getCellData(String key){
        DataFormatter formatter = new DataFormatter();
        // formatter.formatCellValue(sheet.getRow(rowValue).getCell(cellValue));
        for(int i = 0; i< getRowCount(); i++){
        String name = formatter.formatCellValue(sheet.getRow(i).getCell(0));
        if(name.equals(key)){
            //System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(1)));
            return formatter.formatCellValue(sheet.getRow(i).getCell(1));
        }
        }
        return null;

    }


    public int getRowCount(){

        rowCount = sheet.getPhysicalNumberOfRows();
        //System.out.println(rowCount);
        return rowCount;
    }
    
}
