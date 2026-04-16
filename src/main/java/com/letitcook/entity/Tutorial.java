package com.letitcook.entity;

public class Tutorial {
    private String title;
    private String type;
    private String url;

    public Tutorial() {}

    public Tutorial(String title, String type, String url) {
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
