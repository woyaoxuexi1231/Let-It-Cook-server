package com.letitcook.controller;

import com.letitcook.entity.Dish;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "*")
public class DishController {

    private final Map<Long, Dish> dishMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public DishController() {
        initSampleData();
    }

    private void initSampleData() {
        Dish dish1 = new Dish();
        dish1.setId(idGenerator.getAndIncrement());
        dish1.setName("宫保鸡丁");
        dish1.setImage("https://example.com/gongbaojiding.jpg");
        dish1.setTutorials(Arrays.asList(
                createTutorial("家常做法", "video", "https://example.com/video1"),
                createTutorial("美食教程", "link", "https://example.com/link1")
        ));
        dishMap.put(dish1.getId(), dish1);

        Dish dish2 = new Dish();
        dish2.setId(idGenerator.getAndIncrement());
        dish2.setName("鱼香肉丝");
        dish2.setImage("https://example.com/yuxiangrousi.jpg");
        dish2.setTutorials(Arrays.asList(
                createTutorial("正宗做法", "video", "https://example.com/video2")
        ));
        dishMap.put(dish2.getId(), dish2);

        Dish dish3 = new Dish();
        dish3.setId(idGenerator.getAndIncrement());
        dish3.setName("糖醋排骨");
        dish3.setImage("https://example.com/tangcupaigu.jpg");
        dish3.setTutorials(new ArrayList<>());
        dishMap.put(dish3.getId(), dish3);
    }

    private com.letitcook.entity.Tutorial createTutorial(String title, String type, String url) {
        com.letitcook.entity.Tutorial tutorial = new com.letitcook.entity.Tutorial();
        tutorial.setTitle(title);
        tutorial.setType(type);
        tutorial.setUrl(url);
        return tutorial;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return new ArrayList<>(dishMap.values());
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Long id) {
        return dishMap.get(id);
    }

    @PostMapping
    public Dish addDish(@RequestBody Dish dish) {
        Long id = idGenerator.getAndIncrement();
        dish.setId(id);
        dishMap.put(id, dish);
        return dish;
    }

    @PutMapping("/{id}")
    public Dish updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        dish.setId(id);
        dishMap.put(id, dish);
        return dish;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteDish(@PathVariable Long id) {
        dishMap.remove(id);
        Map<String, String> result = new HashMap<>();
        result.put("message", "删除成功");
        return result;
    }

    @GetMapping("/random")
    public List<Dish> getRandomDishes(@RequestParam(value = "count", defaultValue = "3") int count) {
        List<Dish> allDishes = new ArrayList<>(dishMap.values());
        if (allDishes.isEmpty()) {
            return new ArrayList<>();
        }
        Collections.shuffle(allDishes);
        int actualCount = Math.min(count, allDishes.size());
        return allDishes.stream().limit(actualCount).collect(Collectors.toList());
    }
}
