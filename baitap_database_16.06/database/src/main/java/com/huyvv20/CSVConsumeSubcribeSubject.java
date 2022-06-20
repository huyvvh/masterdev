package com.huyvv20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVConsumeSubcribeSubject {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://localhost:8000/masterdev_huyvv20";
        String username = "root";
        String password = "Huyvanvo2692001";

        String filePath = "E:/masterdev/baitap_database_16.06/database/data/subcribe.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection = DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql = "insert into subcribe_subject(id_class,id_student,id_subject,score,rank) values(?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 1;
            lineReader.readLine();
            while((lineText=lineReader.readLine()) != null){
                String[] data = lineText.split(",");

                String id_class = data[0];
                String id_student = data[1];
                String id_subject = data[2];
                String score = data[3];
                String rank = data[4];

                System.out.print(id_student);
                preparedStatement.setInt(1,parseInt(id_class));
                preparedStatement.setInt(2,parseInt(id_student));
                preparedStatement.setInt(3,parseInt(id_subject));
                preparedStatement.setInt(4,parseInt(score));
                preparedStatement.setString(5,rank);
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
