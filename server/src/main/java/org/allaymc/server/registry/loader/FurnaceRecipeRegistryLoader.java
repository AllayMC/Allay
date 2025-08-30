package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.recipe.impl.FurnaceRecipe;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
@Slf4j
public class FurnaceRecipeRegistryLoader implements RegistryLoader<Void, Map<Identifier, FurnaceRecipe>> {
    @Override
    public Map<Identifier, FurnaceRecipe> load(Void $) {
        log.info(I18n.get().tr(TrKeys.ALLAY_RECIPE_FURNACE_LOADING));
        var furnaceRecipes = new Object2ObjectOpenHashMap<Identifier, FurnaceRecipe>();
        var stream = Objects.requireNonNull(Utils.getResource("recipes.json"));

        var obj = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
        var furnaceAuxRecipes = obj.getAsJsonArray("furnaceAux");

        for (var furnaceAuxRecipe : furnaceAuxRecipes) {
            var recipe = parseFurnaceAux(furnaceAuxRecipe.getAsJsonObject());
            furnaceRecipes.put(recipe.getIdentifier(), recipe);
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_RECIPE_FURNACE_LOADED, furnaceRecipes.size()));
        return furnaceRecipes;
    }

    protected FurnaceRecipe parseFurnaceAux(JsonObject obj) {
        return FurnaceRecipe
                .builder()
                .ingredient(RecipeJsonUtils.parseDefaultItemDescriptor(obj.getAsJsonObject("input")))
                .output(RecipeJsonUtils.parseOutput(obj.getAsJsonObject("output")))
                .tag(obj.get("tag").getAsString())
                .build();
    }
}
