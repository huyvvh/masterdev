package com.huyvv20.object;

public class subject {
    private int id_subject;
    private String subject_name;
    private int credit;

    public subject(int id_subject, String subject_name, int credit) {
        this.id_subject = id_subject;
        this.subject_name = subject_name;
        this.credit = credit;
    }

    public subject() {
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
