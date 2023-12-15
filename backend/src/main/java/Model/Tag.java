package Model;

import DTO.TagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    //bidirecitonal setter
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        recipe.getTags().add(this);
    }

    public Tag(TagDTO tagDTO) {
        this.id = tagDTO.id;
        this.name = tagDTO.name;
    }




    public Tag() {
    }
}
