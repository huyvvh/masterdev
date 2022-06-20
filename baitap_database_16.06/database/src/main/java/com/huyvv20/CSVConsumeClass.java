package com.huyvv20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVConsumeClass {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://172.17.80.26:3306/masterdev_huyvv20";
        String username = "huyvv20";
        String password = "74utrFVHBm7uWhVv";

        String filePath = "E:/masterdev/baitap_database_16.06/database/data/class.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection = DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql = "insert into class(id_class,class_name) values(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 1;
            lineReader.readLine();
            while((lineText=lineReader.readLine()) != null){
                String[] data = lineText.split(",");

                String id_class = data[0];
                String class_name = data[1];

                preparedStatement.setInt(1,parseInt(id_class));
                preparedStatement.setString(2,class_name);
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
