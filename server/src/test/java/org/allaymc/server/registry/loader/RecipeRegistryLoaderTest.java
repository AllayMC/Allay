package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.CHARCOAL;
import static org.allaymc.api.item.type.ItemTypes.SAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * {@code RecipeRegistryLoaderTest} verifies furnace recipe loading across current shapeless
 * data and the legacy object-valued input/output representation.
 */
@ExtendWith(AllayTestExtension.class)
class RecipeRegistryLoaderTest {

    /**
     * Loads the bundled recipe resource after item registration. The resulting registry must
     * contain the tag-based log cooking recipe and the expected total number of furnace recipes.
     */
    @Test
    void testLoadCurrentFurnaceRecipes() {
        var recipes = new RecipeRegistryLoader().load(null);
        var tagRecipeId = FurnaceRecipe.buildIdentifier(
                new ItemTagDescriptor(ItemTags.LOGS_THAT_BURN),
                FurnaceRecipe.Type.FURNACE
        );

        assertInstanceOf(FurnaceRecipe.class, recipes.get(tagRecipeId));
        assertEquals(220, recipes.values().stream().filter(FurnaceRecipe.class::isInstance).count());
    }

    /**
     * Parses a current furnace entry whose ingredient is an item tag. Conversion must preserve
     * that tag, its charcoal output, its explicit negative priority, and its furnace station type.
     */
    @Test
    void testParseFurnaceLikeShapelessTagRecipe() {
        var json = JsonParser.parseString("""
                {
                  "id": "minecraft:furnace_log",
                  "input": [{"count": 1, "tag": "minecraft:logs_that_burn"}],
                  "output": [{"count": 1, "item": "minecraft:charcoal"}],
                  "priority": -1,
                  "tag": "furnace"
                }
                """).getAsJsonObject();

        var recipe = RecipeRegistryLoader.parseFurnaceLikeShapeless(json);
        var ingredient = assertInstanceOf(ItemTagDescriptor.class, recipe.getIngredient());

        assertEquals(ItemTags.LOGS_THAT_BURN, ingredient.getItemTag());
        assertEquals(CHARCOAL, recipe.getOutput().getItemType());
        assertEquals(-1, recipe.getPriority());
        assertEquals(FurnaceRecipe.Type.FURNACE, recipe.getType());
    }

    /**
     * Parses a legacy furnace entry with object-valued input and output fields. The recipe must
     * retain the sand ingredient and furnace type while defaulting its omitted priority to zero.
     */
    @Test
    void testParseLegacyFurnaceRecipe() {
        var json = JsonParser.parseString("""
                {
                  "input": {"item": "minecraft:sand"},
                  "output": {"count": 1, "item": "minecraft:glass"},
                  "tag": "furnace"
                }
                """).getAsJsonObject();

        var recipe = RecipeRegistryLoader.parseFurnace(json);
        var ingredient = assertInstanceOf(ItemTypeDescriptor.class, recipe.getIngredient());

        assertEquals(SAND, ingredient.getItemType());
        assertEquals(0, recipe.getPriority());
        assertEquals(FurnaceRecipe.Type.FURNACE, recipe.getType());
    }
}
