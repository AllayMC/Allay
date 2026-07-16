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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class RecipeRegistryLoaderTest {

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
