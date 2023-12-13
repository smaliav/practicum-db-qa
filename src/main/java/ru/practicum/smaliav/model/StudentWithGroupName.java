package ru.practicum.smaliav.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentWithGroupName {
    private Integer id;
    private String name;
    private String groupName;
}
