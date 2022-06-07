package org.example;

import example.avro.Employee;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

public class Desirialize {
    //Deserialize sample avro file
    public static void reader() {
        File avroOutput = new File("src/main/resources/employee-test.avro");
        try {
        DatumReader<Employee> employeeDatumReader = new SpecificDatumReader(Employee.class);
        DataFileReader<Employee> dataFileReader = new DataFileReader<Employee>(avroOutput, employeeDatumReader);
        Employee e = null;
        while (dataFileReader.hasNext()) {
            e = dataFileReader.next(e);
            System.out.println(e);
        }
        } catch(IOException e) {System.out.println("Error reading");}
    }
}
