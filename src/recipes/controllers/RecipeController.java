package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.models.Recipe;
import recipes.services.RecipeService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RestController
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(@Autowired RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable @Min(1) long id){
        Recipe recipe = recipeService.findRecipeById(id);
        if (recipe == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find recipe with id:" + id);
        }
        return recipe;
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> getRecipe(@RequestParam(required = false) String name, @RequestParam(required = false) String category){
        if (name == null && category == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must supply a search parameter");
        }
        if (name != null && category != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can only search by name or category");
        }

        if(name != null){
            return recipeService.findAllByName(name);
        }else {
            return recipeService.findAllByCategory(category);
        }
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable @Min(1) long id, @RequestBody @Valid  Recipe recipe, Principal principal){
        RecipeExistsAndAccessIsValid(id, principal);
        recipe.setId(id);
        recipe.setUsername(principal.getName().toLowerCase(Locale.ROOT));
        recipeService.save(recipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable @Min(1) long id, Principal principal){
        RecipeExistsAndAccessIsValid(id, principal);
        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> addRecipe(@RequestBody @Valid  Recipe recipe, Principal principal){
        if(principal == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You're not authorized to perform this action");
        }
        recipe.setUsername(principal.getName().toLowerCase(Locale.ROOT));
        System.out.println("A POST request with username: " + principal.getName());
        Long id = recipeService.save(recipe).getId();
        return Collections.singletonMap("id", id);
    }

    private void RecipeExistsAndAccessIsValid(long id, Principal principal) {
        String username = recipeService.getUsernameById(id);
        if (username == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find recipe with id:" + id);
        }else if (principal == null || !username.equals(principal.getName().toLowerCase(Locale.ROOT))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You're not authorized to perform this action");
        }
    }

}
