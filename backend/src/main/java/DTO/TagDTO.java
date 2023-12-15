package DTO;

import Model.Recipe;
import Model.Tag;

import java.util.List;
import java.util.Set;

public class TagDTO {
    public int id;
    public String name;
    public List<Recipe> recipes;

    public TagDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDTO(String name, List<Recipe> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    public TagDTO(int id, String name, List<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.recipes = recipes;
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }


    public TagDTO() {
    }
}
