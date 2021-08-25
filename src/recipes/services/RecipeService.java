package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.models.Recipe;
import recipes.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public Recipe save(Recipe toSave) {
        toSave.updateDate();
        return recipeRepository.save(toSave);
    }

    public boolean existsById(Long id){
        return recipeRepository.existsById(id);
    }

    public void delete(Long id){
        recipeRepository.deleteById(id);
    }

    public List<Recipe> findAllByCategory(String category){
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findAllByName(String name){
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public String getUsernameById(long id){
        return recipeRepository.getUsernameById(id);
    }
}
