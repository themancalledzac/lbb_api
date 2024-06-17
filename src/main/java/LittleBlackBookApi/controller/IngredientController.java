package LittleBlackBookApi.controller;

import LittleBlackBookApi.model.IngredientModel;
import LittleBlackBookApi.services.IngredientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/getIngredient/{ingredientId}")
    public IngredientModel getIngredient(@PathVariable("ingredientId") String ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }
}
