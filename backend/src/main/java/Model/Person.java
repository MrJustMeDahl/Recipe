package Model;

import DTO.PersonDTO;
import DTO.RecipeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String email;
    private String role;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public Person(PersonDTO personDTO) {
        this.id = personDTO.id;
        this.name = personDTO.name;
        this.password = personDTO.password;
        this.email = personDTO.email;
        this.role = personDTO.role;
        this.recipes = new ArrayList<>();
        for(RecipeDTO recipeDTO : personDTO.recipes){
            this.addRecipe(new Recipe(recipeDTO));
        }
    }

    //bidirecitonal setter
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public Person() {
    }
}
