package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.item.recipe.DecoratedPotRecipe;
import org.allaymc.server.item.recipe.FireworkRecipe;

import java.io.InputStreamReader;
import java.util.*;

/**
 * {@code RecipeRegistryLoader} converts bundled recipe data into runtime crafting, furnace,
 * smithing, potion, and complex recipes. Each entry is indexed by the identifier supplied by
 * its constructed recipe, and later entries replace earlier recipes with the same identifier.
 * Deprecated entries are omitted from the shaped, shapeless, and legacy furnace sections.
 *
 * <p>
 * Current resource layouts can place cooking recipes in the shapeless section. Station tags
 * route those entries to furnace construction so they keep their cooking-station identity
 * instead of becoming ordinary crafting recipes. Older layouts use the optional
 * {@code "furnaceAux"} section, which is also read through the furnace parser. Both paths
 * therefore supply the same runtime recipe abstraction while accepting their different input
 * and output object shapes.
 *
 * @author daoge_cmd
 */
public class RecipeRegistryLoader implements RegistryLoader<Void, Map<Identifier, Recipe>> {
    /**
     * Loads bundled recipes into a mutable identifier map and adds the built-in complex recipes.
     * Deprecated shaped, shapeless, and legacy furnace entries are omitted, while malformed
     * supported entries abort loading rather than returning the accumulated map.
     *
     * <p>
     * Station tags distinguish cooking entries inside the current shapeless section from ordinary
     * crafting entries. The optional legacy furnace section is parsed separately into the same
     * furnace abstraction, preserving station behavior across both resource layouts. Later
     * duplicate identifiers overwrite earlier recipes, including across sections.
     */
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

            // Current data places furnace recipes in the shapeless section. Preserve their
            // station-specific cooking behavior instead of treating them as ordinary crafting recipes.
            var recipe = isFurnaceLike(shapelessRecipe.getAsJsonObject()) ?
                    parseFurnaceLikeShapeless(shapelessRecipe.getAsJsonObject()) :
                    parseShapeless(shapelessRecipe.getAsJsonObject());
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
        // Older resource layouts store furnace entries separately. Read that optional section
        // as well so legacy data retains its cooking recipes alongside current-format entries.
        var furnaceRecipeElement = obj.get("furnaceAux");
        if (furnaceRecipeElement != null && !furnaceRecipeElement.isJsonNull()) {
            var furnaceRecipes = furnaceRecipeElement.getAsJsonArray();
            for (var furnaceRecipe : furnaceRecipes) {
                if (isDeprecated(furnaceRecipe.getAsJsonObject())) {
                    continue;
                }

                var recipe = parseFurnace(furnaceRecipe.getAsJsonObject());
                recipes.put(recipe.getIdentifier(), recipe);
            }
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
        complexRecipes.add(DecoratedPotRecipe.INSTANCE);
        complexRecipes.forEach(complexRecipe -> recipes.put(complexRecipe.getIdentifier(), complexRecipe));

        return recipes;
    }

    /**
     * Returns whether the recipe carries the explicit {@code "deprecated"} station tag.
     */
    protected static boolean isDeprecated(JsonObject obj) {
        return obj.has("tag") && obj.get("tag").getAsString().equals("deprecated");
    }

    /**
     * Returns whether the recipe tag names a supported furnace-like cooking station.
     */
    protected static boolean isFurnaceLike(JsonObject obj) {
        if (!obj.has("tag")) {
            return false;
        }

        return switch (obj.get("tag").getAsString()) {
            case "furnace", "blast_furnace", "smoker", "campfire", "soul_campfire" -> true;
            default -> false;
        };
    }

    /**
     * Creates a smithing-trim recipe from template, base, and addition descriptors. Missing
     * priority
     * defaults to zero, and the recipe receives {@code null} outputs because its result depends
     * on the actual smithing input.
     */
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

    /**
     * Creates a smithing-transform recipe with parsed outputs and three ingredient descriptors.
     * Missing priority defaults to zero, while item and tag identity resolution follows
     * {@code RecipeJsonUtils} and propagates malformed-data failures.
     */
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

    /**
     * Creates an unordered crafting, stonecutter, or cartography recipe from the supplied fields.
     * Missing priority defaults to zero, and unsupported station tags fail instead of being
     * interpreted as ordinary crafting recipes.
     */
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

    /**
     * Creates a cooking recipe from the first input and output in a shapeless-format entry.
     * The station tag selects the furnace variant, priority defaults to zero, and additional
     * inputs or outputs are not included by this conversion.
     */
    protected static FurnaceRecipe parseFurnaceLikeShapeless(JsonObject obj) {
        var input = obj.getAsJsonArray("input").get(0).getAsJsonObject();

        return new FurnaceRecipe(
                RecipeJsonUtils.parseItemDescriptor(input),
                RecipeJsonUtils.parseOutput(obj.getAsJsonArray("output").get(0).getAsJsonObject()),
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                FurnaceRecipe.Type.valueOf(obj.get("tag").getAsString().toUpperCase(Locale.ROOT))
        );
    }

    /**
     * Creates a shaped recipe from pattern rows and character-to-ingredient mappings. Input keys
     * must be nonempty single-character strings because only their first character is retained;
     * missing priority defaults to zero, and output decoding follows {@code RecipeJsonUtils}.
     */
    protected static ShapedRecipe parseShaped(JsonObject obj) {
        // Pattern
        List<String> patternList = new ArrayList<>();
        obj.getAsJsonArray("pattern").forEach(line -> {
            patternList.add(line.getAsString());
        });

        // Keys
        Map<Character, ItemDescriptor> keys = new HashMap<>();
        obj.getAsJsonObject("input").entrySet().forEach(k -> {
            // Recipe patterns address one character per ingredient. Longer JSON keys lose
            // their suffix here and can overwrite another mapping with the same first character.
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

    /**
     * Creates a cooking recipe from legacy object-valued input and output fields. The station tag
     * selects the furnace variant, missing priority defaults to zero, and malformed ingredient or
     * output data propagates parsing failures.
     */
    protected static FurnaceRecipe parseFurnace(JsonObject obj) {
        return new FurnaceRecipe(
                RecipeJsonUtils.parseItemDescriptor(obj.getAsJsonObject("input")),
                RecipeJsonUtils.parseOutput(obj.getAsJsonObject("output")),
                obj.has("priority") ? obj.get("priority").getAsInt() : 0,
                FurnaceRecipe.Type.valueOf(obj.get("tag").getAsString().toUpperCase(Locale.ROOT))
        );
    }

    /**
     * Creates a potion recipe from one-item input, output, and reagent stacks.
     */
    protected static PotionRecipe parsePotion(JsonObject obj) {
        return new PotionRecipe(
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("input")),
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("output")),
                RecipeJsonUtils.parseItemStack(obj.getAsJsonObject("reagent"))
        );
    }
}
