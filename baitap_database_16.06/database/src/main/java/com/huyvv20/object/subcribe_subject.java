package com.huyvv20.object;

public class subcribe_subject {

    private int id_class;
    private int id_student;
    private int id_subject;
    private int score;
    private String rank;

    public subcribe_subject(int id_class, int id_student, int id_subject, int score, String rank) {
        this.id_class = id_class;
        this.id_student = id_student;
        this.id_subject = id_subject;
        this.score = score;
        this.rank = rank;
    }

    public subcribe_subject() {
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
