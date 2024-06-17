package LittleBlackBookApi.services;

import LittleBlackBookApi.model.IngredientModel;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientService {

    @Transactional(readOnly = true)
    IngredientModel getIngredientById(String ingredientId);
}
