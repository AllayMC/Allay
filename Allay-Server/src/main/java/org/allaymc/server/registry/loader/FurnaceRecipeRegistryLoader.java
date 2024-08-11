package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
@Slf4j
public class FurnaceRecipeRegistryLoader implements RegistryLoader<Void, Map<Identifier, FurnaceRecipe>> {
    @Override
    public Map<Identifier, FurnaceRecipe> load(Void $) {
        log.info(I18n.get().tr(TrKeys.A_FURNACERECIPE_LOADING));
        var furnaceRecipes = new Object2ObjectOpenHashMap<Identifier, FurnaceRecipe>();
        var stream = Objects.requireNonNull(Utils.getResource("recipes.json"));

        var obj = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
        var furnaceAuxRecipes = obj.getAsJsonArray("furnaceAux");
        var initialMax = furnaceAuxRecipes.size();
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(initialMax)
                .setTaskName("Loading Vanilla Furnace Recipes")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            for (var furnaceAuxRecipe : furnaceAuxRecipes) {
                var recipe = parseFurnaceAux(furnaceAuxRecipe.getAsJsonObject());
                furnaceRecipes.put(recipe.getIdentifier(), recipe);
                pgbar.step();
            }
        }
        log.info(I18n.get().tr(TrKeys.A_FURNACERECIPE_LOADED, furnaceRecipes.size()));
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
