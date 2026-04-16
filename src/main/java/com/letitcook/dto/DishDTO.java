package com.letitcook.dto;

import com.letitcook.entity.Tutorial;

import java.util.List;

/**
 * 菜谱DTO，用于前端传输数据
 *
 * @author hulei
 * @since 2026/4/16
 */
public class DishDTO {
    private Long id;
    private String name;
    private String image;
    private List<Tutorial> tutorials;

    public DishDTO() {}

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
