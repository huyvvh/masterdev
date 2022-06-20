package com.huyvv20.object;

public class teacher {
    private int id_teacher;
    private String teacher_name;

    private String gender;

    public teacher(int id_teacher, String teacher_name, String gender) {
        this.id_teacher = id_teacher;
        this.teacher_name = teacher_name;
        this.gender = gender;
    }

    public teacher() {
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
