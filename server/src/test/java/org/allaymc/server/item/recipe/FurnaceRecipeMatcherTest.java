package org.allaymc.server.item.recipe;

import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class FurnaceRecipeMatcherTest {

    @Test
    void testMatchTagRecipe() {
        var recipe = FurnaceRecipeMatcher.match(OAK_LOG.createItemStack(), FurnaceRecipe.Type.FURNACE, true);

        assertNotNull(recipe);
        assertInstanceOf(ItemTagDescriptor.class, recipe.getIngredient());
        assertEquals(CHARCOAL, recipe.getOutput().getItemType());

        Registries.ITEMS.getContent().values().stream()
                .filter(itemType -> itemType.hasItemTag(ItemTags.LOGS_THAT_BURN))
                .forEach(itemType -> {
                    var taggedRecipe = FurnaceRecipeMatcher.match(
                            itemType.createItemStack(),
                            FurnaceRecipe.Type.FURNACE,
                            true
                    );
                    assertNotNull(taggedRecipe, itemType.getIdentifier().toString());
                    assertEquals(CHARCOAL, taggedRecipe.getOutput().getItemType());
                });
    }

    @Test
    void testConcreteRecipeTakesPrecedence() {
        var recipe = FurnaceRecipeMatcher.match(MANGROVE_LOG.createItemStack(), FurnaceRecipe.Type.FURNACE, true);

        assertNotNull(recipe);
        assertInstanceOf(ItemTypeDescriptor.class, recipe.getIngredient());
        assertEquals(CHARCOAL, recipe.getOutput().getItemType());
    }

    @Test
    void testNormalFurnaceFallback() {
        var smokerRecipe = FurnaceRecipeMatcher.match(BEEF.createItemStack(), FurnaceRecipe.Type.SMOKER, true);
        assertNotNull(smokerRecipe);
        assertEquals(FurnaceRecipe.Type.SMOKER, smokerRecipe.getType());

        var recipe = FurnaceRecipeMatcher.match(SAND.createItemStack(), FurnaceRecipe.Type.SMOKER, true);

        assertNotNull(recipe);
        assertEquals(FurnaceRecipe.Type.FURNACE, recipe.getType());
        assertNull(FurnaceRecipeMatcher.match(SAND.createItemStack(), FurnaceRecipe.Type.CAMPFIRE, false));
    }
}
