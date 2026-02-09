package com.fx.dto;

public class FilmDto {

    private String score;
    private String name;

    public FilmDto(){
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", this.name, this.score);
    }
}
