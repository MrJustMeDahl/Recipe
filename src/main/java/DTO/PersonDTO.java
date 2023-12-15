package DTO;

import lombok.Getter;

@Getter
public class PersonDTO {
    public int id;
    public String name;
    public String password;
    public String email;
    public String role;


    public PersonDTO(int id, String name, String password, String email, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public PersonDTO(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public PersonDTO(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
