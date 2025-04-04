package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.NetworkRecipe;
import org.allaymc.api.item.recipe.impl.ShapedRecipe;
import org.allaymc.api.item.recipe.impl.ShapelessRecipe;
import org.allaymc.api.item.recipe.impl.SmithingTransformRecipe;
import org.allaymc.api.item.recipe.impl.SmithingTrimRecipe;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.InputStreamReader;
import java.util.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class RecipeRegistryLoader implements RegistryLoader<Void, Int2ObjectMap<NetworkRecipe>> {
    @Override
    public Int2ObjectMap<NetworkRecipe> load(Void $) {
        log.info(I18n.get().tr(TrKeys.A_RECIPE_LOADING));
        var recipes = new Int2ObjectOpenHashMap<NetworkRecipe>();
        var stream = Objects.requireNonNull(Utils.getResource("recipes.json"));

        var obj = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();

        var shapedRecipes = obj.getAsJsonArray("shaped");
        for (var shapedRecipe : shapedRecipes) {
            var recipe = parseShaped(shapedRecipe.getAsJsonObject());
            recipes.put(recipe.getNetworkId(), recipe);
        }

        var shapelessRecipes = obj.getAsJsonArray("shapeless");
        for (var shapelessRecipe : shapelessRecipes) {
            var recipe = parseShapeless(shapelessRecipe.getAsJsonObject());
            recipes.put(recipe.getNetworkId(), recipe);
        }

        var smithingTransformRecipes = obj.getAsJsonArray("smithingTransform");
        for (var smithingTransformRecipe : smithingTransformRecipes) {
            var recipe = parseSmithingTransform(smithingTransformRecipe.getAsJsonObject());
            recipes.put(recipe.getNetworkId(), recipe);
        }

        var smithingTrimRecipes = obj.getAsJsonArray("smithingTrim");
        for (var smithingTrimRecipe : smithingTrimRecipes) {
            var recipe = parseSmithingTrim(smithingTrimRecipe.getAsJsonObject());
            recipes.put(recipe.getNetworkId(), recipe);
        }

        log.info(I18n.get().tr(TrKeys.A_RECIPE_LOADED, recipes.size()));
        return recipes;
    }

    protected SmithingTrimRecipe parseSmithingTrim(JsonObject obj) {
        return SmithingTrimRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .template(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("template")))
                .base(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("base")))
                .addition(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("addition")))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }

    protected SmithingTransformRecipe parseSmithingTransform(JsonObject obj) {
        return SmithingTransformRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .template(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("template")))
                .base(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("base")))
                .addition(RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("addition")))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .outputs(RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new))
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }

    protected ShapelessRecipe parseShapeless(JsonObject obj) {
        // Ingredients
        List<ItemDescriptor> ingredients = new ArrayList<>();
        obj.getAsJsonArray("input").forEach(item -> {
            ingredients.add(RecipeJsonUtils.parseItemDescriptor(item.getAsJsonObject()));
        });

        return ShapelessRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .ingredients(ingredients.toArray(ItemDescriptor[]::new))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .outputs(RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new))
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }

    protected ShapedRecipe parseShaped(JsonObject obj) {
        // Pattern
        List<String> patternList = new ArrayList<>();
        obj.getAsJsonArray("pattern").forEach(line -> {
            patternList.add(line.getAsString());
        });

        // Keys
        Map<Character, ItemDescriptor> keys = new HashMap<>();
        obj.getAsJsonObject("input").entrySet().forEach(k -> {
            // the length of k.getKey() must be 1 so we can safely convert it to a char
            var key = k.getKey().charAt(0);
            var itemDescriptor = RecipeJsonUtils.parseItemDescriptor(k.getValue().getAsJsonObject());
            keys.put(key, itemDescriptor);
        });

        return ShapedRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .pattern(ShapedRecipe.PatternHelper.build(patternList))
                .keys(keys)
                .outputs(RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new))
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }
}
