package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.item.recipe.FireworkRecipe;

import java.io.InputStreamReader;
import java.util.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class RecipeRegistryLoader implements RegistryLoader<Void, Map<Identifier, Recipe>> {
    @SneakyThrows
    @Override
    public Map<Identifier, Recipe> load(Void $) {
        var recipes = new HashMap<Identifier, Recipe>();
        JsonObject obj;
        try (var reader = new InputStreamReader(Objects.requireNonNull(Utils.getResource("recipes.json")))) {
            obj = JsonParser.parseReader(reader).getAsJsonObject();
        }

        // Shaped
        var shapedRecipes = obj.getAsJsonArray("shaped");
        for (var shapedRecipe : shapedRecipes) {
            if (isDeprecated(shapedRecipe.getAsJsonObject())) {
                continue;
            }

            var recipe = parseShaped(shapedRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Shapeless
        var shapelessRecipes = obj.getAsJsonArray("shapeless");
        for (var shapelessRecipe : shapelessRecipes) {
            if (isDeprecated(shapelessRecipe.getAsJsonObject())) {
                continue;
            }

            var recipe = parseShapeless(shapelessRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Smithing Transform
        var smithingTransformRecipes = obj.getAsJsonArray("smithingTransform");
        for (var smithingTransformRecipe : smithingTransformRecipes) {
            var recipe = parseSmithingTransform(smithingTransformRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Smithing Trim
        var smithingTrimRecipes = obj.getAsJsonArray("smithingTrim");
        for (var smithingTrimRecipe : smithingTrimRecipes) {
            var recipe = parseSmithingTrim(smithingTrimRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Furnace
        var furnaceRecipes = obj.getAsJsonArray("furnaceAux");
        for (var furnaceRecipe : furnaceRecipes) {
            if (isDeprecated(furnaceRecipe.getAsJsonObject())) {
                continue;
            }

            var recipe = parseFurnace(furnaceRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Potion
        var potionRecipes = obj.getAsJsonArray("potionMixes");
        for (var potionRecipe : potionRecipes) {
            var recipe = parsePotion(potionRecipe.getAsJsonObject());
            recipes.put(recipe.getIdentifier(), recipe);
        }

        // Complex
        var complexRecipes = new HashSet<ComplexRecipe>();
        complexRecipes.add(FireworkRecipe.INSTANCE);
        complexRecipes.forEach(complexRecipe -> recipes.put(complexRecipe.getIdentifier(), complexRecipe));

        return recipes;
    }

    protected static boolean isDeprecated(JsonObject obj) {
        return obj.has("tag") && obj.get("tag").getAsString().equals("deprecated");
    }

    protected static SmithingTrimRecipe parseSmithingTrim(JsonObject obj) {
        return new SmithingTrimRecipe(
                new Identifier(obj.get("id").getAsString()),
                null,
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("template")),
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("base")),
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("addition"))
        );
    }

    protected static SmithingTransformRecipe parseSmithingTransform(JsonObject obj) {
        return new SmithingTransformRecipe(
                new Identifier(obj.get("id").getAsString()),
                RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new),
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("template")),
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("base")),
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("addition"))
        );
    }

    protected static ShapelessRecipe parseShapeless(JsonObject obj) {
        // Ingredients
        List<ItemDescriptor> ingredients = new ArrayList<>();
        obj.getAsJsonArray("input").forEach(item -> {
            ingredients.add(RecipeJsonUtils.parseItemDescriptor(item.getAsJsonObject()));
        });

        return new ShapelessRecipe(
                new Identifier(obj.get("id").getAsString()),
                RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new),
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                ingredients.toArray(ItemDescriptor[]::new),
                switch (obj.get("tag").getAsString()) {
                    case "crafting_table" -> ShapelessRecipe.Type.CRAFTING;
                    case "stonecutter" -> ShapelessRecipe.Type.STONECUTTER;
                    case "cartography_table" -> ShapelessRecipe.Type.CARTOGRAPHY_TABLE;
                    default -> throw new IllegalStateException("Unhandled tag for shapeless recipe: " + obj.get("tag").getAsString());
                }
        );
    }

    protected static ShapedRecipe parseShaped(JsonObject obj) {
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

        return new ShapedRecipe(
                new Identifier(obj.get("id").getAsString()),
                RecipeJsonUtils.parseOutputs(obj).toArray(ItemStack[]::new),
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                ShapedRecipe.PatternHelper.build(patternList),
                keys
        );
    }

    protected static FurnaceRecipe parseFurnace(JsonObject obj) {
        return new FurnaceRecipe(
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("input")),
                RecipeJsonUtils.parseOutput(obj.getAsJsonObject("output")),
                FurnaceRecipe.Type.valueOf(obj.get("tag").getAsString().toUpperCase(Locale.ROOT))
        );
    }

    protected static PotionRecipe parsePotion(JsonObject obj) {
        return new PotionRecipe(
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("input")),
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("output")),
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("reagent"))
        );
    }
}
