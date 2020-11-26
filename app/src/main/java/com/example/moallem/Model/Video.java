package com.example.moallem.Model;

import java.io.Serializable;

public class Video implements Serializable {
    private String link;

    public Video() {
    }

    public Video(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
