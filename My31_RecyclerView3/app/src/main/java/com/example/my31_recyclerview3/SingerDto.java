package com.example.my31_recyclerview3;

import java.io.Serializable;

public class SingerDto implements Serializable {
    String name, writer, score;
    int resId;
    String url;

    public SingerDto(String name, String writer, String score, int resId, String url) {
        this.name = name;
        this.writer = writer;
        this.score = score;
        this.resId = resId;
        this.url = url;
    }

    public SingerDto(String name, String writer, int resId) {
        this.name = name;
        this.writer = writer;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}