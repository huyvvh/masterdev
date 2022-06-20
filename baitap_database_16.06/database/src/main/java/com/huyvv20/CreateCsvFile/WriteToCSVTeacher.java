package com.huyvv20.CreateCsvFile;

import com.huyvv20.object.teacher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WriteToCSVTeacher {
    static String[] firstname = {"Nguyễn","Lê","Vũ","Phan","Công","Bùi","Đinh","Cao","Huỳnh","Hồ"};
    static String[] secondname_men = {"Văn","Đức","Minh","Anh","Bá"};
    static String[] secondname_women = {"Thị","Anh","Phương","Anh"};
    static String[] lastname_men = {"Huy","Hoàng","Tiến","Sơn","Dũng","Bình","Hải","Lộc","Tùng","Ánh"};
    static String[] lastname_women = {"Hồng","Đào","Chi","Hạnh","Dung","Hiền","Thùy","Trang","Nhung","Thư"};
    static Random rd = new Random();
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void main(String[] args) {
        String fileName = "E:/masterdev/baitap_database_16.06/database/data/teacher.csv";
        writeCsvFile(fileName);
    }

    public static void writeCsvFile(String fileName){
        List<teacher> teacherList = new ArrayList<>();
        for(int i = 0; i<1000; i++){
            teacher teacher = new teacher();
            teacher.setId_teacher(100 + i);
            if(rd.nextInt(2) == 1){
                String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_women[rd.nextInt(3)] + " " + lastname_women[rd.nextInt(9)]);
                teacher.setTeacher_name(name);
                teacher.setGender("Woman");
            } else {
                String name = String.valueOf(firstname[rd.nextInt(9)] + " " + secondname_men[rd.nextInt(3)] + " " + lastname_men[rd.nextInt(9)]);
                teacher.setTeacher_name(name);
                teacher.setGender("Man");
            }
            teacherList.add(teacher);
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            //fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            //fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (teacher teacher : teacherList) {
                fileWriter.append(String.valueOf(teacher.getId_teacher()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(teacher.getTeacher_name());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(teacher.getGender());
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
