package com.huyvv20.object;

public class classes {
    private int id_class;
    private String class_name;

    public classes(int id_class, String class_name) {
        this.id_class = id_class;
        this.class_name = class_name;
    }

    public classes() {
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
