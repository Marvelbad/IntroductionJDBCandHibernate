package org.example.bean;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column()
    String name;

    @Column()
    String lastName;

    @Column()
    byte age;
}
