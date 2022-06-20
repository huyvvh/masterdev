package com.huyvv20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVConsumeTeacher {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://localhost:8000/masterdev_huyvv20";
        String username = "root";
        String password = "Huyvanvo2692001";

        String filePath = "E:/masterdev/baitap_database_16.06/database/data/teacher.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection = DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql = "insert into teacher(id_teacher,teacher_name,gender) values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;
            lineReader.readLine();
            while((lineText=lineReader.readLine()) != null){
                String[] data = lineText.split(",");

                String id_teacher = data[0];
                String teacher_name = data[1];
                String gender = data[2];

                preparedStatement.setInt(1,parseInt(id_teacher));
                preparedStatement.setString(2,teacher_name);
                preparedStatement.setString(3,gender);
                preparedStatement.addBatch();

                if(count%batchSize==0){
                    preparedStatement.executeBatch();
                }
            }
            lineReader.close();
            preparedStatement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
