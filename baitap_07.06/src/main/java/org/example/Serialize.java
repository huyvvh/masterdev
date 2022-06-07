package org.example;

import example.avro.Employee;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Serialize {
    public static void main(Employee[] args){
        ArrayList<Employee> employeeList = new ArrayList<>(Arrays.asList(args));
        File avroOutput = new File("src/main/resources/employee-test.avro");
        try {
            DatumWriter<Employee> employeeDatumWriter = new SpecificDatumWriter<Employee>(Employee.class);
            DataFileWriter<Employee> dataFileWriter = new DataFileWriter<Employee>(employeeDatumWriter);
            dataFileWriter.create(employeeList.get(0).getSchema(), avroOutput);
            for(int i=0; i<employeeList.size(); i++){
                dataFileWriter.append(employeeList.get(i));
            }
            dataFileWriter.close();
        } catch (IOException e) {System.out.println("Error writing");}
    }
}
