package de.sainth.recipemanager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.sainth.recipemanager.db.RecipeRepository;
import de.sainth.recipemanager.db.model.OverviewRecipe;
import de.sainth.recipemanager.db.model.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
  private final RecipeRepository recipeRepository;

  @Autowired
  public HomeController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping("/")
  public ModelAndView index(Map<String, Object> model) {
    List<OverviewRecipe> recipes = recipeRepository.overviewQuery();
    model.put("recipes", recipes);
    return new ModelAndView("index", model);
  }

  @GetMapping("/recipe")
  public ModelAndView showRecipe(@RequestParam long id, Map<String, Object> model) {
    Recipe recipe = recipeRepository.query(id);
    model.put("recipe", recipe);
    return new ModelAndView("showRecipe", model);
  }

  @GetMapping("createRecipe")
  public String createRecipe() {
    return "createRecipe";
  }

  @PostMapping("createRecipe")
  public String authenticate(@RequestParam Map<String, String> map,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
    Recipe recipe = new Recipe(map.get("name"), Short.valueOf(map.get("portions")));
    recipe.setDescription(map.get("description"));
    recipeRepository.add(recipe);
    return "redirect:/";
  }
}
