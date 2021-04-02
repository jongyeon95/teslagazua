package com.jongyeon.teslagazua.entity;

import com.jongyeon.teslagazua.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getRoleKey(){
        return this.role.getKey();
    }

    public User update(String name){
        this.name = name;
        return this;
    }

}
