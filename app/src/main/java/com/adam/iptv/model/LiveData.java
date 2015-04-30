package com.adam.iptv.model;

/**
 * Created by adam on 2/8/2015.
 */
public class LiveData {

    private  String id;
    private  String path;
    private  String desk;


    public LiveData() {

    }

    public LiveData(String id, String path, String desk) {
        this.setId(id);
        this.setPath(path);
        this.setDesk(desk);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}
