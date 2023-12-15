package DTO;

import Model.Person;
import Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    public int id;
    public String name;
    public String password;
    public String email;
    public String role;
    public List<RecipeDTO> recipes;


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

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.password = person.getPassword();
        this.email = person.getEmail();
        this.role = person.getRole();
        this.recipes = new ArrayList<>();
        for(Recipe recipe : person.getRecipes()){
            this.recipes.add(new RecipeDTO(recipe));
        }
    }

    public PersonDTO() {
    }
}
