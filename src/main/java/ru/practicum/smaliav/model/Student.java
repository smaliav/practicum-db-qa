package ru.practicum.smaliav.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
    private Integer id;
    private String name;
    private int groupId;

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }
}
