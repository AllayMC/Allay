package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.recipe.impl.PotionMixRecipe;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author IWareQ
 */
@Slf4j
public class PotionMixRecipeRegistryLoader implements RegistryLoader<Void, Map<Identifier, PotionMixRecipe>> {
    @Override
    public Map<Identifier, PotionMixRecipe> load(Void $) {
        log.info(I18n.get().tr(TrKeys.A_RECIPE_POTIONMIX_LOADING));
        var stream = Objects.requireNonNull(Utils.getResource("recipes.json"));

        var obj = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
        var potionMixRecipes = obj.getAsJsonArray("potionMixes");

        Map<Identifier, PotionMixRecipe> recipes = new HashMap<>(potionMixRecipes.size());
        for (var potionMixRecipe : potionMixRecipes) {
            var recipe = parseRecipe(potionMixRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        log.info(I18n.get().tr(TrKeys.A_RECIPE_POTIONMIX_LOADED, recipes.size()));
        return recipes;
    }

    protected PotionMixRecipe parseRecipe(JsonObject obj) {
        return PotionMixRecipe
                .builder()
                .ingredient(RecipeJsonUtils.parseDefaultItemDescriptor(obj.getAsJsonObject("input")))
                .output(RecipeJsonUtils.parseDefaultItemDescriptor(obj.getAsJsonObject("output")))
                .reagent(RecipeJsonUtils.parseDefaultItemDescriptor(obj.getAsJsonObject("reagent")))
                .build();
    }
}
