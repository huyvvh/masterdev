package com.huyvv20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVConsumeSubject {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://localhost:8000/masterdev_huyvv20";
        String username = "root";
        String password = "Huyvanvo2692001";

        String filePath = "E:/masterdev/baitap_database_16.06/database/data/subject.csv";

        int batchSize=200;

        Connection connection=null;


        try{
            connection = DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql = "insert into subject(id_subject,subject_name,credit) values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 1;
            lineReader.readLine();
            while((lineText=lineReader.readLine()) != null){
                String[] data = lineText.split(",");

                String id_project = data[0];
                String subject_name = data[1];
                String credit = data[2];

                preparedStatement.setInt(1,parseInt(id_project));
                preparedStatement.setString(2,subject_name);
                preparedStatement.setInt(3,parseInt(credit));
                preparedStatement.addBatch();
                if(count%batchSize==0){
                    preparedStatement.executeBatch();
                    connection.commit();
                }
                count++;
            }

            preparedStatement.executeBatch();
            connection.commit();
            connection.close();
            lineReader.close();
            System.out.println("Data has been inserted successfully.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
