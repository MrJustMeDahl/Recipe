package DTO;

import Model.Recipe;
import Model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipeDTO {
    public int id;
    public String name;
    public String description;
    public String ingredients;
    public String instructions;
    public List<TagDTO> tags;
    public PersonDTO person;

    public RecipeDTO(int id, String name, String description, String ingredients, String instructions, List<TagDTO> tags, PersonDTO author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
        this.person = author;
    }


    public RecipeDTO(String name, String description, String ingredients, String instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    public RecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.instructions = recipe.getInstructions();
        this.tags = new ArrayList<>();
        for(Tag tag : recipe.getTags()){
            this.tags.add(new TagDTO(tag));
        }
        if(recipe.getPerson() == null){
            this.person = null;
        }
        else {
            this.person = new PersonDTO(recipe.getPerson());
        }

    }
    //Bidirectional setter for person
    public void setPerson(PersonDTO person) {
        this.person = person;
        if(person.recipes != null){
            person.recipes.add(this);
        }
    }

    public RecipeDTO() {
    }


}
