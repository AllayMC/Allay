package org.allaymc.server.item.recipe;

import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code FireworkRecipeTest} checks dynamic firework outputs using registered
 * crafting ingredients. The cases cover rocket duration and carried explosions,
 * new-star color and effect construction, and fade-color replacement, using
 * fresh item fixtures so recipe state can be inspected directly.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class FireworkRecipeTest {
    /**
     * Crafts rockets with one through three gunpowder slots and with an
     * existing star. Duration must follow the gunpowder count, and star
     * explosion data must appear in the produced rocket.
     */
    @Test
    void testCraftFireworkRocket() {
        assertEquals(
                1,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        air(), air()
                ))[0]).getDuration()
        );
        assertEquals(
                2,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        gunpowder(), air()
                ))[0]).getDuration()
        );
        assertEquals(
                3,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        gunpowder(), gunpowder()
                ))[0]).getDuration()
        );

        var explosion = new FireworkExplosion(
                FireworkType.SMALL_SPHERE, List.of(DyeColor.WHITE),
                Collections.emptyList(), true, true
        );
        var fireworkStar = fireworkStar();
        fireworkStar.setExplosion(explosion);
        var rocket = (ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                paper(), gunpowder(),
                gunpowder(), fireworkStar
        ))[0];
        assertEquals(2, rocket.getDuration());
        assertEquals(explosion, rocket.getExplosions().toArray()[0]);
    }

    /**
     * Crafts a star from three dyes, gunpowder, a diamond, and a fire charge.
     * The output must retain dye order, use the huge-sphere shape, enable
     * trail, and leave flicker disabled.
     */
    @Test
    void testCraftFireworkStar() {
        var input = new CraftingRecipeInput(
                blueDye(), pinkDye(), whiteDye(),
                diamond(), gunpowder(), fireCharge(),
                air(), air(), air()
        );

        var output = (ItemFireworkStarStack) FireworkRecipe.INSTANCE.getOutputs(input)[0];
        var explosion = output.getExplosion();
        assertEquals(FireworkType.HUGE_SPHERE, explosion.type());
        assertEquals(List.of(DyeColor.BLUE, DyeColor.PINK, DyeColor.WHITE), explosion.colors());
        assertEquals(Collections.emptyList(), explosion.fadeColors());
        assertFalse(explosion.flicker());
        assertTrue(explosion.trail());
    }

    /**
     * Supplies one existing star and three dye colors to fade-color crafting.
     * The output explosion must contain the supplied fade colors in flattened
     * crafting-grid order.
     */
    @Test
    void testAddFadeColorToFireStar() {
        var input = new CraftingRecipeInput(
                blueDye(), pinkDye(), whiteDye(),
                air(), fireworkStar(), air(),
                air(), air(), air()
        );
        var output = (ItemFireworkStarStack) FireworkRecipe.INSTANCE.getOutputs(input)[0];
        var explosion = output.getExplosion();
        assertEquals(List.of(DyeColor.BLUE, DyeColor.PINK, DyeColor.WHITE), explosion.fadeColors());
    }

    /**
     * Returns the shared air stack used for empty crafting positions.
     */
    private ItemAirStack air() {
        return ItemTypes.AIR.createItemStack();
    }

    /**
     * Creates a gunpowder ingredient stack for the firework crafting fixtures.
     */
    private ItemGunpowderStack gunpowder() {
        return ItemTypes.GUNPOWDER.createItemStack();
    }

    /**
     * Creates a paper ingredient stack for the rocket crafting fixtures.
     */
    private ItemPaperStack paper() {
        return ItemTypes.PAPER.createItemStack();
    }

    /**
     * Creates a blue dye stack for explosion color ordering checks.
     */
    private ItemDyeStack blueDye() {
        return ItemTypes.BLUE_DYE.createItemStack();
    }

    /**
     * Creates a pink dye stack for explosion color ordering checks.
     */
    private ItemDyeStack pinkDye() {
        return ItemTypes.PINK_DYE.createItemStack();
    }

    /**
     * Creates a white dye stack for explosion color ordering checks.
     */
    private ItemDyeStack whiteDye() {
        return ItemTypes.WHITE_DYE.createItemStack();
    }

    /**
     * Creates a firework-star stack for explosion and fade-color crafting
     * fixtures.
     */
    private ItemFireworkStarStack fireworkStar() {
        return ItemTypes.FIREWORK_STAR.createItemStack();
    }

    /**
     * Creates a diamond ingredient stack for the star trail-effect fixture.
     */
    private ItemDiamondStack diamond() {
        return ItemTypes.DIAMOND.createItemStack();
    }

    /**
     * Creates a fire-charge stack for the huge-sphere star shape fixture.
     */
    private ItemFireChargeStack fireCharge() {
        return ItemTypes.FIRE_CHARGE.createItemStack();
    }
}