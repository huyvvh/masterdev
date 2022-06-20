package com.huyvv20.CreateCsvFile;

import com.huyvv20.object.classes;
import com.huyvv20.object.teacher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WriteToCSVClass {

    static Random rd = new Random();
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "id_class;class_name";

    public static void main(String[] args) {
        String fileName = "E:/masterdev/baitap_database_16.06/database/data/class.csv";
        writeCsvFile(fileName);
    }

    public static void writeCsvFile(String fileName) {
        List<classes> classesList = new ArrayList<>();
        for(int i=0; i<1000; i++){
            classes classes = new classes();
            classes.setId_class(i+1);
            classes.setClass_name("Class_" + String.valueOf(i+1));
            classesList.add(classes);
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (classes classes : classesList) {
                fileWriter.append(String.valueOf(classes.getId_class()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(classes.getClass_name());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }

    }

}
