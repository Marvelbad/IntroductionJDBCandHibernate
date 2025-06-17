package org.example.bean;


import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String lastName;
    private byte age;
}
