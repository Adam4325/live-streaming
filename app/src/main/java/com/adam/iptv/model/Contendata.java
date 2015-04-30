package com.adam.iptv.model;

import java.util.ArrayList;

/**
 * Created by adam on 2/8/2015.
 */
public class Contendata {
    private String id_content;
    private String judul;
    private String coverUrl;
    private String create_date;
    private String contentIsi;
    private int hit;

    public Contendata(){

    }
    public Contendata(String id_content,String judul,String coverUrl,String create_date,String contentIsi,int hit){

        this.id_content = id_content;
        this.judul = judul;
        this.coverUrl = coverUrl;
        this.create_date = create_date;
        this.contentIsi = contentIsi;
        this.hit = hit;

    }

    public String getId_content() {
        return this.id_content;
    }

    public void setId_content(String id_content) {
        this.id_content = id_content;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getContentIsi() {
        return contentIsi;
    }

    public void setContentIsi(String contentIsi) {
        this.contentIsi = contentIsi;
    }
}
