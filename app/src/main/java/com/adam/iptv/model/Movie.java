package com.adam.iptv.model;

/**
 * Created by adam on 2/7/2015.
 */
import java.util.ArrayList;

public class Movie {
    private String title, thumbnailUrl;
    private int tanggal;
    private double hit;
    private ArrayList<String> contents;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, int tanggal, double hit,
                 ArrayList<String> contents) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.tanggal = tanggal;
        this.hit = hit;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getTanggal() {
        return tanggal;
    }

    public void setTanggal(int year) {
        this.tanggal = year;
    }

    public double getHit() {
        return hit;
    }

    public void setHit(double hit) {
        this.hit = hit;
    }

    public ArrayList<String> getGenre() {
        return contents;
    }

    public void setGenre(ArrayList<String> content) {
        this.contents = contents;
    }

}