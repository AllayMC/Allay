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

/**
 * {@code FurnaceRecipeMatcherTest} checks registered furnace lookup across
 * concrete ingredients, item tags, and furnace-category fallback. The cases use
 * built-in log and cooking recipes to protect lookup precedence, charcoal
 * outputs for tagged logs, and optional reuse of ordinary furnace recipes.
 */
@ExtendWith(AllayTestExtension.class)
class FurnaceRecipeMatcherTest {

    /**
     * Looks up oak logs and every registered type tagged as burnable logs. Each
     * ingredient must resolve to a furnace recipe producing charcoal, with oak
     * demonstrating tag-descriptor lookup.
     */
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

    /**
     * Looks up mangrove logs where a concrete furnace recipe is available. The
     * matcher must return a concrete item descriptor and the expected charcoal
     * output rather than a competing tag recipe.
     */
    @Test
    void testConcreteRecipeTakesPrecedence() {
        var recipe = FurnaceRecipeMatcher.match(MANGROVE_LOG.createItemStack(), FurnaceRecipe.Type.FURNACE, true);

        assertNotNull(recipe);
        assertInstanceOf(ItemTypeDescriptor.class, recipe.getIngredient());
        assertEquals(CHARCOAL, recipe.getOutput().getItemType());
    }

    /**
     * Looks up an exact smoker recipe and an ingredient requiring
     * ordinary-furnace fallback. The assertions protect exact-category
     * precedence, successful enabled fallback, and a missing result when
     * campfire fallback is disabled.
     */
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
