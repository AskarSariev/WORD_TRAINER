package ru.sariev.word_trainer_app.models;

import javax.persistence.*;

@Entity
@Table(name = "Words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "original")
    private String original;

    @Column(name = "translation")
    private String translation;

    @Column(name = "status")
    private boolean status;

    public Word() {
    }

    public Word(String original, String translation, boolean status) {
        this.original = original;
        this.translation = translation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", translation='" + translation + '\'' +
                ", status=" + status +
                '}';
    }
}
