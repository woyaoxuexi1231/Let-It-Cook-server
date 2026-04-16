package com.letitcook.entity;

import java.util.List;

public class Dish {
    private Long id;
    private String name;
    private String image;
    private List<Tutorial> tutorials;

    public Dish() {}

    public Dish(Long id, String name, String image, List<Tutorial> tutorials) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tutorials = tutorials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Tutorial> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials = tutorials;
    }
}
