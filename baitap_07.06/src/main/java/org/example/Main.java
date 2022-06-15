package org.example;

import example.avro.Employee;
import example.avro.Salary;

public class Main {
    public static void main(String[] args) {
        Serialize serialize = new Serialize();
        Desirialize desirialize = new Desirialize();
        Employee e1 = new Employee();
        e1.setName("John");
        e1.setId(111);
        e1.setSex("male");
        e1.setAge(22);
        e1.setAddress("Thanh Xuan");
        Salary s1 = new Salary();
        s1.setNetSalary(5000);
        s1.setTax(50);
        e1.setSalary(s1);

        Employee e2 = new Employee();
        e2.setName("Mikel");
        e2.setId(112);
        e2.setSex("female");
        e2.setAge(27);
        e2.setAddress("Dong Da");
        Salary s2 = new Salary();
        s2.setNetSalary(3000);
        s2.setTax(30);
        e2.setSalary(s2);

        Employee e3 = new Employee();
        e3.setName("Jack");
        e3.setId(113);
        e3.setSex("male");
        e3.setAge(24);
        e3.setAddress("Ha Dong");
        Salary s3 = new Salary();
        s3.setNetSalary(1000);
        s3.setTax(100);
        e3.setSalary(s3);

        serialize.main(new Employee[]{e1, e2, e3});

        desirialize.reader();
    }
}