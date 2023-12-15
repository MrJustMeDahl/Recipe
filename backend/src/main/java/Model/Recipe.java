package Model;

import DTO.RecipeDTO;
import DTO.TagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String instructions;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    public Recipe(RecipeDTO recipeDTO) {
        this.id = recipeDTO.id;
        this.name = recipeDTO.name;
        this.description = recipeDTO.description;
        this.ingredients = recipeDTO.ingredients;
        this.instructions = recipeDTO.instructions;
        this.tags = new ArrayList<>();
        for(TagDTO tagDTO : recipeDTO.tags){
            this.tags.add(new Tag(tagDTO));
        }
        this.person = new Person(recipeDTO.person);
    }

    //Bidirectional setter (Check for null)
    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getRecipes().add(this);
    }
    public void setPerson(Person person) {
        this.person = person;
        if(person.getRecipes() == null){
            person.setRecipes(new ArrayList<>());
        }
        person.addRecipe(this);

    }

    public Recipe() {
    }
}
