package com.assignment3.h2setup;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InputReader {

    public static List<String[]> readDataFromCsv(String filePath, String filename) throws NoSuchFileException {

        Reader reader;
        List<String[]> csvData;
        try {
            reader = Files.newBufferedReader((Paths.get(filePath + filename)));
            CSVReader csvReader = new CSVReader(reader);
            csvData = csvReader.readAll();
            return csvData.stream().skip(1).collect(Collectors.toList());

        } catch (NoSuchFileException e) {
            throw new NoSuchFileException("wrong path or filename");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String[]> readDataFromXls(String filePath, String filename) throws FileNotFoundException {
        List<String[]> dataList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath + filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);

            for (int r = 1; r <= 9; r++) {
                HSSFRow row = sheet.getRow(r);
                String[] dataRow = new String[2];
                for (int c = 0; c <= 1; c++) {
                    HSSFCell cell = row.getCell(c);
                    dataRow[c] = cell.toString();
                }
                dataList.add(dataRow);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("wrong path or filename");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
