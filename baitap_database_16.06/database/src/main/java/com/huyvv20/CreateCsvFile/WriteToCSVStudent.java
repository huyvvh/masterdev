package com.huyvv20.CreateCsvFile;

import com.huyvv20.object.student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WriteToCSVStudent {
    // Delimiter used in CSV file
    static String[] firstname = {"Nguyễn","Lê","Vũ","Phan","Công","Bùi","Đinh","Cao","Huỳnh","Hồ"};
    static String[] secondname_men = {"Văn","Đức","Minh","Anh","Bá"};
    static String[] secondname_women = {"Thị","Anh","Phương","Anh"};
    static String[] lastname_men = {"Huy","Hoàng","Tiến","Sơn","Dũng","Bình","Hải","Lộc","Tùng","Ánh"};
    static String[] lastname_women = {"Hồng","Đào","Chi","Hạnh","Dung","Hiền","Thùy","Trang","Nhung","Thư"};
    static Random rd = new Random();
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    // CSV file header
    private static final String FILE_HEADER = "id_student;fullname;gender;birthday";

    public static void main(String[] args) {
        String fileName = "E:/masterdev/baitap_database_16.06/database/data/student.csv";
        writeCsvFile(fileName);
    }

    public static void writeCsvFile(String fileName) {
        // Create new Countrys objects
        List<student> studentList = new ArrayList<>();
        for(int i=0; i<10000 ; i++){
            student student = new student();
            if(i<3000){
                student.setId_student(17000+i);
                if(rd.nextInt(2) == 1){
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_women[rd.nextInt(3)] + " " + lastname_women[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Woman");
                } else {
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_men[rd.nextInt(3)] + " " + lastname_men[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Man");
                }
                student.setBirthday(rd.nextInt(31) + "/" + rd.nextInt(12) + "/1999");
                studentList.add(student);
            } else if (i>=3000 && i<6000){
                student.setId_student(18000+i);
                if(rd.nextInt(2) == 1){
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_women[rd.nextInt(3)] + " " + lastname_women[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Woman");
                } else {
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_men[rd.nextInt(3)] + " " + lastname_men[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Man");
                }
                student.setBirthday(rd.nextInt(31) + "/" + rd.nextInt(12) + "/2000");
                studentList.add(student);
            } else if (i>=6000 && i<10000){
                student.setId_student(20000+i);
                if(rd.nextInt(2) == 1){
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_women[rd.nextInt(3)] + " " + lastname_women[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Woman");
                } else {
                    String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_men[rd.nextInt(3)] + " " + lastname_men[rd.nextInt(9)]);
                    student.setFullname(name);
                    student.setGender("Man");
                }
                student.setBirthday(rd.nextInt(31) + "/" + rd.nextInt(12) + "/2001");
                studentList.add(student);
            }
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (student student : studentList) {
                fileWriter.append(String.valueOf(student.getId_student()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(student.getFullname());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(student.getGender());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(student.getBirthday());
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