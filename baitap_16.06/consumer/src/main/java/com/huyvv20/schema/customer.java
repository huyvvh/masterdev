package com.huyvv20.schema;

import com.opencsv.bean.CsvBindByPosition;
import lombok.ToString;

@ToString
public class customer {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private int num_order;
    @CsvBindByPosition(position = 2)
    private int age;
    @CsvBindByPosition(position = 3)
    private String tel;

    public int getId() {
        return id;
    }

    public int getNum_order() {
        return num_order;
    }

    public int getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }
}
