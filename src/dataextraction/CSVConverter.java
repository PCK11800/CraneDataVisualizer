package dataextraction;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;

public class CSVConverter {

    private File inputFile;
    private String savePath;
    private String fileName, saveName;

    public CSVConverter(File inputFile)
    {
        this.inputFile = inputFile;
        initConverter();
        convert();
    }

    private void initConverter()
    {
        fileName = inputFile.getName();
        if(fileName.endsWith(".csv"))
        {
            saveName = fileName.replace(".csv", ".xlsx");
        }
        savePath = inputFile.getAbsolutePath();
        savePath = savePath.replace(fileName, saveName);
    }

    private void convert()
    {
        try{
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            String currentLine = null;
            int rowNum = 0;
            FileInputStream fis = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(";");
                rowNum++;
                SXSSFRow currentRow = sheet.createRow(rowNum);
                for(int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }
            FileOutputStream fos = new FileOutputStream(savePath);
            workbook.write(fos);
            fos.close();
            System.out.println("File Converted");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public File getOutputFile()
    {
        File file = new File(savePath);
        return file;
    }
}
