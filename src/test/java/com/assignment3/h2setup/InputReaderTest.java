package com.assignment3.h2setup;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static com.assignment3.h2setup.H2SetupApplication.PATH;
import static org.assertj.core.api.Assertions.*;

class InputReaderTest {
    final static String CORRECT_PATH = PATH;
    final static String WRONG_PATH = "/falscher/path/";
    final static String CORRECT_CSV_FILENAME = "Ring Chart Data.csv";
    final static String WRONG_CSV_FILENAME = "Data.csv";
    final static String CORRECT_XLS_FILENAME = "piechart-data.xls";
    final static String WRONG_XLS_FILENAME = "piechart-data";


    //right inputReader tests
    @Test
    void readDataFromCsvCorrectTest() throws NoSuchFileException {
        List<String[]> dataFromCsv = InputReader
                .readDataFromCsv(PATH, CORRECT_CSV_FILENAME);
        String[] firstElement = {"2014-12-12", "Apple", "0.3"};
        String[] lastElement = {"2014-12-13", "München Mag Dich T-Shirts GmbH", "0.1"};
        assertThat(dataFromCsv).hasSize(6).contains(firstElement, lastElement);
    }

    @Test
    void readDataFromXlsCorrectTest() throws NoSuchFileException, FileNotFoundException {
        List<String[]> dataFromXsl = InputReader
                .readDataFromXls(PATH, CORRECT_XLS_FILENAME);
        String[] firstElement = {"Deutschland", "36.95"};
        String[] lastElement = {"Liquidität/Terminkontrakte", "4.71"};
        assertThat(dataFromXsl).hasSize(9).contains(firstElement, lastElement);
    }

    //wrong inputReader tests--------------------------------------------------------------------------------

    //wrong path but right filename
    @Test
    void readDataFromCsvWrongPathTest() {

        assertThatExceptionOfType(NoSuchFileException.class)
                .isThrownBy(() -> {
                    InputReader.readDataFromCsv(WRONG_PATH, CORRECT_CSV_FILENAME);
                })
                .withMessage("wrong path or filename");
    }

    //right path but wrong filename
    @Test
    void readDataFromCsvWrongFilenameTest() {

        assertThatExceptionOfType(NoSuchFileException.class)
                .isThrownBy(() -> {
                    InputReader.readDataFromCsv(CORRECT_PATH, WRONG_CSV_FILENAME);
                })
                .withMessage("wrong path or filename");
    }

    //wrong path and right filename
    @Test
    void readDataFromXlsWrongPathTest() {

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> {
                    InputReader.readDataFromXls(WRONG_PATH, CORRECT_XLS_FILENAME);
                })
                .withMessage("wrong path or filename");
    }

    //right path and wrong filename
    @Test
    void readDataFromXlsWrongFilenameTest() {

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> {
                    InputReader.readDataFromXls(CORRECT_PATH, WRONG_XLS_FILENAME);
                })
                .withMessage("wrong path or filename");
    }
}