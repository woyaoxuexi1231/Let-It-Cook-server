package com.letitcook.vo;

import com.letitcook.entity.Tutorial;
import lombok.Data;

import java.util.List;

@Data
public class DishDetailVO {

    private Long id;
    private String name;
    private String image;
    private String cuisine;
    private String cookingTime;
    private String ingredients;
    private List<Tutorial> tutorials;
}
