package com.huyvv20.object;

public class student {
    private int id_student;
    private String fullname;
    private String gender;
    private String birthday;

    public student(int id_student, String fullname, String gender, String birthday) {
        this.id_student = id_student;
        this.fullname = fullname;
        this.gender = gender;
        this.birthday = birthday;
    }

    public student() {
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return id_student + ";" + fullname + ";" + gender + ";" + birthday;
    }
}
