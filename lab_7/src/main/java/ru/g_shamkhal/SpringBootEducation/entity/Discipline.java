package ru.g_shamkhal.SpringBootEducation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ACADEMIC_DISCIPLINES")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "math")
    private String math;

    @Column(name = "history")
    private String history;

    @Column(name = "language")
    private String language;

    @Column(name = "hourse")
    private int hourse;

    // Конструкторы, геттеры и сеттеры
    public Discipline() {}

    public Discipline(String math, String history, String language, int hourse) {
        this.math = math;
        this.history = history;
        this.language = language;
        this.hourse = hourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getHourse() {
        return hourse;
    }

    public void setHourse(int hourse) {
        this.hourse = hourse;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", math='" + math + '\'' +
                ", history='" + history + '\'' +
                ", language='" + language + '\'' +
                ", hourse=" + hourse +
                '}';
    }
}
