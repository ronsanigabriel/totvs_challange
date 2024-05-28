package com.totvs.challenge._shared;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

    public static List<String[]> readCSV(File file) throws Exception {
        List<String[]> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] line;
            boolean firstLine = true;
            while ((line = csvReader.readNext()) != null) {
                if(firstLine){ firstLine = false; continue; }
                String value = line[2];
                BigDecimal decimalValue = new BigDecimal(value.replace(",", "."));
                line[2] = decimalValue.toString();
                records.add(line);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return records;

    }

}
