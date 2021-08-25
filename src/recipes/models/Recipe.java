package recipes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="recipe")
public class Recipe{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String description;

    @Column
    @NotBlank
    private String category;

    @Column(name = "updatedAt")
    private String date;

    @ElementCollection
    @NotNull
    @Size(min=1)
    private List<String> ingredients;

    @ElementCollection
    @NotNull
    @Size(min=1)
    private List<String> directions;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;


    public Recipe(){}
    public Recipe(String name, String description, String[] ingredients, String[] directions, User user){
        this.name = name;
        this.description = description;
        this.ingredients = Arrays.asList(ingredients);
        this.directions = Arrays.asList(directions);
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public String getDate(){return date;}
    public List<String> getIngredients(){return ingredients;}
    public List<String> getDirections(){return directions;}
    public String getUsername() {return username;}

    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setCategory(String category){this.category = category;}
    public void setDate(String date){this.date = date;}
    public void setIngredients(List<String> ingredients){this.ingredients = ingredients;}
    public void setDirections(List<String> directions){this.directions = directions;}
    public void setUsername(String username) {this.username = username;}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void updateDate(){
        this.date = LocalDateTime.now().toString();
    }


}